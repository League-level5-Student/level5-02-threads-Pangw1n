package _04_Thread_Pool;

import java.util.ArrayDeque;

public class WorkQueue implements Runnable{
	
	Thread[] threads;
	boolean isRunning = true;
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
			System.out.println(Thread.currentThread().getId());
		}
	}
	public void shutDown()
	{
		isRunning = false;
	}

	public int getThreadCount()
	{
		return threads.length;
	}
	
	
	
	
	public void addJob(Job j)
	{
		jobQueue.add(j);
	}
}
