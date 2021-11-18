package event;

public abstract class Event implements Comparable<Event> {
	
	private long date;
	
	public Event(long date) {
		this.date = date;
	}

	public long getDate() {
		return date;
	}
	
	public abstract void execute();

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