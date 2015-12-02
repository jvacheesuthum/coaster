

/**
 * Complete the implementation of this class in line with the FSP model
 */

import display.*;

public class Controller {

  public static int Max = 9;
  protected NumberCanvas passengers;
  int pasCount = 0;
  int inCar = 0;

  // declarations required


  public Controller(NumberCanvas nc) {
    passengers = nc;
  }

  public synchronized void newPassenger() throws InterruptedException {
     // complete implementation
     // use "passengers.setValue(integer value)" to update diplay
	  while (pasCount >= Max) wait();
	  if (pasCount < Max){
		  pasCount++;
	  } else {
		  throw new InterruptedException();
	  }
	  passengers.setValue(pasCount);
	  notifyAll();
	  
  }

  public synchronized int getPassengers(int mcar) throws InterruptedException {
     // complete implementation for part I
     // update for part II
     // use "passengers.setValue(integer value)" to update diplay
	  //this returns no. of pas got on car
	  if (!(mcar > 0)){ // && pasCount >= mcar
		  throw new InterruptedException();
	  }
	  
	  while (pasCount < mcar) wait();
	  
	  inCar = mcar;
	  pasCount -= mcar;
	  passengers.setValue(pasCount);
	  return inCar;
  }

  public synchronized void goNow() {
    // complete implementation for part II
  }

}

