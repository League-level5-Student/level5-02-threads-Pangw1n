package _01_Olympic_Rings;

import java.awt.Color;

import org.jointheleague.graphical.robot.Robot;

public class OlympicRings_Threaded {
	// Make A Program that uses Threads and robots to draw the Olympic rings. One robot should draw one ring simultaneously with the other 4 robots.
	public static final Color[] colors = {new Color(0, 125, 200), new Color(255, 175, 0), Color.black, new Color(0, 150, 0), Color.red};
	public static void main(String[] args) {
		Robot[] robots = new Robot[5];
		for (int i = 0; i < 5; i++)
		{
			robots[i] = new Robot((i + 1) * 200, i % 2 == 0 ? 200 : 400);
			robots[i].setPenColor(colors[i]);
			robots[i].setPenWidth(10);
		}
		Thread[] threads = new Thread[5];
		for (int i = 0; i < 5; i++)
		{
			Robot robot = robots[i];
			threads[i] = new Thread(() -> {
				robot.penDown();
				for (int theta = 0; theta <= 360; theta++)
				{
					robot.move(3);
					robot.setAngle(theta);
				}
				robot.penUp();
				robot.hide();
			});
		}
		for (Thread t : threads)
		{
			t.start();
		}
	}
}

