package _04_Thread_Pool;

public class ThreadPoolTester {

	public ThreadPoolTester() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		WorkQueue wq = new WorkQueue();
		System.out.println("Total threads: " + wq.getThreadCount());
		wq.shutDown();
	}

}
