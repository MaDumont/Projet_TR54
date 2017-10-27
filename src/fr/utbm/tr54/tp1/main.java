package fr.utbm.tr54.tp1;

import java.awt.Robot;
import java.io.FileWriter;
import java.io.IOException;

import fr.utbm.tr54.strategie.*;
import lejos.hardware.lcd.LCD;

public class main {

	
	private static MotorsController motors;
	private static SensorsController sensors;
	private static Strategie  strategie;

	public static void main(String[] args){

		motors = new MotorsController();		
		sensors = new SensorsController();


		LCD.clear();
		LCD.drawChar('S', 1, 1);
		LCD.drawChar('T', 2, 1);
		LCD.drawChar('A', 3, 1);
		LCD.drawChar('R', 4, 1);
		LCD.drawChar('T', 5, 1);
		LCD.drawChar('!', 6, 1);
		LCD.refresh();
		
		strategie = new FollowYourFriend1Point(1,5,5);
		voila(1,5,5);
		
		strategie = new FollowYourFriend1Point(10,5,5);
		voila(10,5,5);
		
		strategie = new FollowYourFriend1Point(1,20,5);		
		voila(1,20,5);
		
		strategie = new FollowYourFriend1Point(1,5,10);
		voila(1,5,10);
		
		
		/*strategie = new RobotLeader(8000);
		leader();*/

		
		




	}


	public static void voila(int a, int d, int cyclingTime) {
		int distanceObjectInFront;
		Move speed;
		int time =0;
		
		LCD.clear();
		LCD.drawInt(a, 1, 1);
		LCD.drawInt(d, 1, 2);
		LCD.drawInt(cyclingTime, 1, 3);
		LCD.refresh();	
		
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {}
		
		try {
			
			FileWriter writer = new FileWriter("1point-80-"+a+"-"+d+"-"+cyclingTime+".csv");
			
			writer.append("Time");
			writer.append(',');
			writer.append("speed");
			writer.append(',');
			writer.append("distance");
			writer.append("\n");
		
		
			for(int i=0;i<200/cyclingTime;i++) {
			
								
				distanceObjectInFront = sensors.distance(10);
				speed = strategie.nextStep(distanceObjectInFront);
				motors.setMotorsSpeed(speed);
				motors.forward();
				

				writer.append(time+"");
				writer.append(',');
				writer.append(speed.getLeftMotorSpeed()+"");
				writer.append(',');
				writer.append(distanceObjectInFront+"");	
				writer.append("\n");

				time++;
				
				LCD.clear();
				LCD.drawInt(i, 1, 4);
				LCD.refresh();
						
			}
			motors.stop();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void leader() {
		while(true) {
			motors.setMotorsSpeed(strategie.nextStep());
			motors.forward();
		}
	}
	/*public void ecrireInformationDistance() throws IOException {

		int distanceObjectInFront;	
		FileWriter writer = new FileWriter("MaDumont.csv");

		writer.append("Time");
		writer.append(',');
		writer.append("3");
		writer.append(',');
		writer.append("6");
		writer.append(',');
		writer.append("9");
		writer.append(',');
		writer.append("12");
		writer.append("\n");
		for(int i =0;i<1000;i++) {

			LCD.clear();

			writer.append(i+"");
			for(int j=3;j<=12;j+=3) {

				distanceObjectInFront = sensors.distance(j);
				LCD.drawInt(j,2,(int)j/3);
				LCD.drawInt(distanceObjectInFront,4,j/3);
				LCD.refresh();

				writer.append(distanceObjectInFront+"");
				writer.append(',');


			}		

		}
	}*/
	







}
