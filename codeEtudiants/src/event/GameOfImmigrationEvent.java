package event;

import jeuDeLImmigration.GameOfImmigration;

public class GameOfImmigrationEvent extends Event {
	private GameOfImmigration immigration;
	public GameOfImmigrationEvent(long date, GameOfImmigration immigration) {
		super(date);
		this.immigration=immigration;
	}

	@Override
	public void execute() {
		immigration.move();
		EventManager.get().addEvent(new GameOfImmigrationEvent(getDate()+1, immigration));
	}
}
