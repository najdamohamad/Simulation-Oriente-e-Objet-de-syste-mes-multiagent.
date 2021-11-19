package event;

import java.util.PriorityQueue;

/*
 * Cette classe est celle chargée de la gestion des evénements
 */
public class EventManager {
	
	private long currentDate;
	private PriorityQueue<Event> evenements;
	
	
	private static final EventManager eventManag = new EventManager();

	/*
	 * Constructeur du gestionnaire des evenements
	 * initialise une FIFO pour stocker les evenements
	 */
	public EventManager() {
		evenements = new PriorityQueue<Event>();
	}

	/*
	 * Getter du gestionnaire d'evenements
	 */
	public static EventManager get() {
		return eventManag;
	}
	
	/*
	 * Ajoute un evenement
	 * @param e evenement
	 */
	public void addEvent(Event e) {
		evenements.add(e);
	}

	/*
	 * Implémente la date et passe à l'evenement suivant
	 */
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
	
	/*
	 * Booleen pour savoir s'il reste des evenements à traiter
	 */
	public boolean isFinished() {
		return evenements.isEmpty();
	}
	
	/*
	 * Reinitialisation du gestionnaire d'evenement
	 */
	public void restart() {
		evenements.clear();
		currentDate = 0;
	}
}