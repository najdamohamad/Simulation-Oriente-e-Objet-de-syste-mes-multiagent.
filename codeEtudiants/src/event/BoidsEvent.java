package event;

import boids.Boids;

public class BoidsEvent extends Event {

  private Boids boids;

	public BoidsEvent(long date, Boids boids) {
		super(date);
    this.boids=boids;
	}

	@Override
	public void execute() {
		boids.move();
		EventManager.get().addEvent(new BoidsEvent(getDate()+1, boids));

	}

}