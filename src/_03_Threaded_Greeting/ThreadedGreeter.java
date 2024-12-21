package _03_Threaded_Greeting;

public class ThreadedGreeter implements Runnable {

	int num;
	
	public ThreadedGreeter(int num) {
		this.num = num;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Hello from thread number: " + num);
		if (num < 50)
		{
			Thread t = new Thread(new ThreadedGreeter(num + 1));
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
