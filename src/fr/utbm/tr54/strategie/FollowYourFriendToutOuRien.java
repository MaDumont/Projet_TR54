package fr.utbm.tr54.strategie;

import fr.utbm.tr54.tp1.Move;

public class FollowYourFriendToutOuRien implements Strategie {
	
	@Override
	public Move nextStep() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Move nextStep(int value) {

		if(value > 15) {
			return new Move(50,50);
		}
		return new Move(0, 0);
	}

	@Override
	public Move nextStep(float distance) {
		
		return null;
	}

}
