package _04_Thread_Pool;

public class ThreadPoolTester {

	public ThreadPoolTester() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		WorkQueue wq = new WorkQueue();
		System.out.println("Total threads: " + wq.getThreadCount());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < 1000; i++)
		{
			int x = i;
			Job j = () -> {
				System.out.println(x);
			};
			wq.addJob(j);
		}
		wq.shutDown();
	}

}
