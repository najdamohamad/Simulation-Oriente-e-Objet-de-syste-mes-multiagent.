package event;

/**Classe abstraite qui est implémentée par le gestionnaire d'evenements */
public abstract class Event implements Comparable<Event> {
	
	private long date;
	
	/**
	 * Creation d'un evenement qui sera traité à la date indiquée en paramètre
	 * 
	 * @param date
	 */
	public Event(long date) {
		this.date = date;
	}
	
	/**
	 * Getter de la date de l'evenement
	 * 
	 * @return date
	 */
	public long getDate() {
		return date;
	}
	/**
	 * Execution de l'evenement
	 */
	
	public abstract void execute();

	/*
	 * Comparaison de deux evenements pour les ordonner
	 */
	public int compareTo(Event evenement) {
		long  i= this.date - evenement.getDate();
		if(i>0) {
			return 1;
		}
		else if(i==0) {
			return 0;
		}
		else {
			return -1;
		}
	}
}