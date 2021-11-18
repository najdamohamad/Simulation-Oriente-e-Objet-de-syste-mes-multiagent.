package event;

import simulateurBalles.Balls;

public class BallsEvent extends Event {
	
	private Balls balles;
	
	public BallsEvent(long date, Balls balles) {
		super(date);
		this.balles=balles;
	}

	@Override
	public void execute() {
		balles.translate(10, 10);
		EventManager.get().addEvent(new BallsEvent(getDate()+1, balles));

	}

}
