package event;

import segregationSchelling.Segregation;

public class SegregationEvent extends Event {

	private Segregation schelling;
	public SegregationEvent(long date, Segregation schelling) {
		super(date);
		this.schelling=schelling;
	}

	@Override
	public void execute() {
		schelling.move();
		EventManager.get().addEvent(new SegregationEvent(getDate()+1, schelling));

	}


}
