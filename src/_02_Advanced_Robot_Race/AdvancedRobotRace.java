package _02_Advanced_Robot_Race;

import java.util.Random;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class AdvancedRobotRace {
	// Re-do the robot race recipe from level 3 module 0. 
	// This time, use threads to make all of the robots go at the same time.
	
	int[] placements = {-1, -1, -1, -1, -1};
	
	public static void main(String[] args) {
		new AdvancedRobotRace().run();
	}
	
	void run()
	{
		Robot drawLine = new Robot(0, 50);
		drawLine.setAngle(90);
		drawLine.setSpeed(100);
		drawLine.setPenWidth(10);
		drawLine.penDown();
		drawLine.moveTo(1500, 50);
		drawLine.penUp();
		drawLine.hide();
		
		Robot[] robots = new Robot[5];
		for (int i = 0; i < 5; i++)
		{
			robots[i] = new Robot((i + 1) * 250, 800);
		}
		Thread[] threads = new Thread[5];
		for (int i = 0; i < 5; i++)
		{
			Robot robot = robots[i];
			int robotNum = i;
			threads[i] = new Thread(() -> {
				Random rand = new Random();
				while (robot.getY() > 50)
				{
					int speed = rand.nextInt(5) + 1;
					robot.setSpeed(speed);
					robot.move(10);
				}
				finish(robotNum);
			});
		}
		for (Thread t : threads)
		{
			t.start();
		}
	}
	
	void finish(int num)
	{
		for (int i = 0; i < placements.length; i++)
		{
			if (placements[i] == -1)
			{
				placements[i] = num;
				if (i == placements.length - 1)
				{
					JOptionPane.showMessageDialog(null, 
						"1st: Robot #" + (placements[0] + 1)
						+ "\n2nd: Robot #" + (placements[1] + 1)
						+ "\n3rd: Robot #" + (placements[2] + 1)
						+ "\n4th: Robot #" + (placements[3] + 1)
						+ "\n5th: Robot #" + (placements[4] + 1)
					);
				}
				return;
			}
		}
	}
}
