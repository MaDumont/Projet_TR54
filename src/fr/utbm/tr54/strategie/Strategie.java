package fr.utbm.tr54.strategie;

import fr.utbm.tr54.tp1.Move;

public interface Strategie {
	
	/**
	 * Next step.
	 *
	 * @return the int[] the speed of each motor
	 */
	public Move nextStep();
	
	/**
	 * Next step.
	 * 
	 * What the robot need to do now that he detect this color.
	 *
	 * @param detectedColor the detected color
	 * @return the int[] the speed of each motor
	 */
	public Move nextStep(int value);
	
	/**
	 * Next step.
	 * 
	 * What the robot need to do now that he know the distance.
	 *
	 * @param distance the distance with the object in front
	 * @return the int[] the speed of each motor
	 */
	public Move nextStep(float distance);

}
