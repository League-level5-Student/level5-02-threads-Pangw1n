package _01_Olympic_Rings;

import org.jointheleague.graphical.robot.Robot;

public class OlympicRings_Threaded {
	// Make A Program that uses Threads and robots to draw the Olympic rings. One robot should draw one ring simultaneously with the other 4 robots.
	public static void main(String[] args) {
		Robot[] robots = new Robot[5];
		for (int i = 0; i < 5; i++)
		{
			robots[i] = new Robot((i + 1) * 200, i % 2 == 0 ? 200 : 400);
		}
		
	}
}

