package _04_Thread_Pool;

import java.lang.Thread.State;
import java.util.ArrayDeque;

public class WorkQueue implements Runnable{
	
	Thread[] threads;
	volatile boolean isRunning = true;
	ArrayDeque<Job> jobQueue = new ArrayDeque<Job>();
	
	public WorkQueue() {
		// TODO Auto-generated constructor stub
		threads = new Thread[Runtime.getRuntime().availableProcessors() - 1];
		for (int i = 0; i < threads.length; i++)
		{
			threads[i] = new Thread(this);
			threads[i].start();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRunning)
		{
			System.out.println("Thread #" + Thread.currentThread().getId());
			if (!performJob())
			{
				synchronized(jobQueue)
				{
					try {
						jobQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void shutDown()
	{
		completeAllJobs();
		
		isRunning = false;
		synchronized(jobQueue)
		{
			jobQueue.notifyAll();
		}
	}

	public int getThreadCount()
	{
		return threads.length;
	}
	
	
	
	
	public void addJob(Job j)
	{
		synchronized(jobQueue)
		{
			jobQueue.add(j);
			jobQueue.notify();
		}
	}
	
	public boolean performJob()
	{
		Job j = null;
		synchronized (jobQueue)
		{
			if (!jobQueue.isEmpty())
			{
				j = jobQueue.remove();
			}
		}

		if (j != null)
		{
			j.perform();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void completeAllJobs()
	{
		while(!jobQueue.isEmpty())
		{
			performJob();
		}
		
		for (int i = 0; i < threads.length; i ++)
		{
			if (threads[i].getState() != State.WAITING)
			{
				i = -1;
			}
		}
	}
}
