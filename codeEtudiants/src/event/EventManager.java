package event;

import java.util.PriorityQueue;

public class EventManager {
	
	private long currentDate;
	private PriorityQueue<Event> evenements;
		private static final EventManager eventManag = new EventManager();


	public EventManager() {
		evenements = new PriorityQueue<Event>();
	}


	public static EventManager get() {
		return eventManag;
	}
	
	public void addEvent(Event e) {
		evenements.add(e);
	}

	
	public void next() {
		Event e = evenements.poll();

		currentDate++;
		
		while (e != null && e.getDate() <= currentDate) {
			e.execute();
			e = evenements.poll();
		}

		if (e != null) {
			evenements.add(e);
		}
	}

	public boolean isFinished() {
		return evenements.isEmpty();
	}
	
	
	public void restart() {
		evenements.clear();
		currentDate = 0;
	}
}