package event;

import boids.Predateurs;

public class PredateursEvent extends Event {

	private Predateurs predateurs;
	
	public PredateursEvent(long date, Predateurs predateurs) {
		super(date);
		this.predateurs=predateurs;
	}

	@Override
	public void execute() {
		predateurs.move();
		EventManager.get().addEvent(new PredateursEvent(getDate()+1, predateurs));

	}

}
