import display.*;

public class Controller {

	protected NumberCanvas passengers;
	
	public static int Max = 9;
	public int pasCount;

	//lock and lock2 are objects for locking - notifying
	private final Object lock = new Object();
	private final Object lock2 = new Object();
	private boolean goNowPressed = false;

	public Controller(NumberCanvas nc) {
		passengers = nc;
		pasCount = 0;
	}
	
	public void newPassenger() throws InterruptedException {
		if(pasCount < Max){
			pasCount++;
			passengers.setValue(pasCount);
			synchronized (lock) {
				lock.notify();
			}
		}
		else{
			synchronized (lock2) {
				lock2.wait();
			}
		}
	}

	public int getPassengers(int mcar) throws InterruptedException {
		while (pasCount < mcar){
			if(goNowPressed){
				int pCount = pasCount;
				pasCount -= pCount;
				passengers.setValue(pasCount);
				synchronized (lock2){
					lock2.notify();
				}
				goNowPressed = !goNowPressed;
				return pCount;
			}			
			else{
				synchronized (lock) {
					lock.wait();
				}
			}
		}
		pasCount -= mcar;
		passengers.setValue(pasCount);
		synchronized (lock2){
			lock2.notify();
		}
		return mcar;
	}

	public synchronized void goNow() {
		if(pasCount > 0){
			goNowPressed = true;
			synchronized (lock){
				lock.notifyAll();
			}
		}
  }
}