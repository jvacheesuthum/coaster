/**
 * Complete the implementation of this class in line with the FSP model
 */

public class PlatformAccess {

	private final Object lock = new Object();
	
	private boolean occupied = false;

	public void arrive() throws InterruptedException {
		if(occupied){			
			synchronized (lock) {
				lock.wait();
			}
		}
		occupied = true;
	}

	public synchronized void depart() {
		occupied = false;
		synchronized (lock) {
			lock.notify();
		}
	}
}
