package event;

import jeuDeLaVieConway.GameOfLife;

public class GameOfLifeEvent extends Event {
	
	private GameOfLife conway;
	public GameOfLifeEvent(long date, GameOfLife conway) {
		super(date);
		this.conway=conway;
	}

	@Override
	public void execute() {
		conway.move();
		EventManager.get().addEvent(new GameOfLifeEvent(getDate()+1, conway));

	}

}
