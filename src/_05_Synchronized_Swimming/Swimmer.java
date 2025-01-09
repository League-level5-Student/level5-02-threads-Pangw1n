package _05_Synchronized_Swimming;

/*
 * This class represents one of the world-class swimmers that would like to take
 * a turn in the pool!
 *
 * Challenge: Complete the run() method, using a for-loop to swim 5 laps by calling
 * the static takeTurn() method in SynchronizedSwimming.
 */
public class Swimmer extends Thread {
	public final String name;
	int laps;

	public Swimmer(String name) {
		this.name = name;
		this.laps = 0;
	}

	@Override
	public void run() {
		// ...
		while (laps < 5)
		{
			SynchronizedSwimming.takeTurn(this);
			laps++;
		}
	}
}
