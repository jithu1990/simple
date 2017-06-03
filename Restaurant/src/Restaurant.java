
class Order {
  private static int i = 0;

  private int count = i++;

//  public Order() {
//    if (count == 10) {
//      System.out.println("Out of food, closing");
//      System.exit(0);
//    }
//  }
    
    public void CreateOrder() {
        if (count == 10) {
          System.out.println("Out of food, closing");
          System.exit(0);
        }
  }

  public String toString() {
    return "Order " + count;
  }
}

class WaitPerson extends Thread {
  private Restaurant restaurant;

  public WaitPerson(Restaurant r) {
    restaurant = r;
    start();
  }

  public void run() {
	  Thread.currentThread().setName("waitperson");
    while (true) {
      if (restaurant.order == null)
        synchronized (this) {
          try {
        	 // System.out.println("waiting");
            wait();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      System.out.println("Waitperson got " + restaurant.order);
      //restaurant.order = null;
      restaurant=null;
      try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
  }
}

class Chef extends Thread {
  private Restaurant restaurant;

  private WaitPerson waitPerson;

  public Chef(Restaurant r, WaitPerson w) {
    restaurant = r;
    waitPerson = w;
    start();
  }

  public void run() {
	  Thread.currentThread().setName("chef");
    while (true) {
      if (restaurant.order == null) {
    	  restaurant.order = new Order();
    	  //System.out.println("creating order");
        restaurant.order.CreateOrder();
        
        System.out.print("Order up! ");
        synchronized (waitPerson) {
          waitPerson.notify();
        }
      }
//      try {
//        //sleep(10);
//      } catch (InterruptedException e) {
//        throw new RuntimeException(e);
//      }
    }
  }
}

public class Restaurant {

  Order order; // Package access

  public static void main(String[] args) {
    Restaurant restaurant = new Restaurant();
    WaitPerson waitPerson = new WaitPerson(restaurant);
    Chef chef = new Chef(restaurant, waitPerson);
  }
} 
