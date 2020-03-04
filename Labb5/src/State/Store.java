package State;

import java.util.ArrayList;

/**
 * The store class represents a store with all the variables a store 
 * can have. The store is changed by all the different events.
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */
public class Store 
{
   private boolean isOpen;
   private int maxCustomers;
   private int registers;
   private double closingTime;
   
   // customers holds all customers, also the ones that aren't in the store.
   private ArrayList<Customer> customers;
   
   // customerFactory can create new customers.
   private CustomerFactory customerFactory;
   private FIFO registerLine;
   
   private int coinMade;
   private int freeRegisters;
   
   private double registerFreeTime;
   private double customerQueueTime;
   private int totalCustomersInQueue;
   private double lastPaymentTime;
   
   /**
    * 
    * @param maxCustomers, the max allowed amount of customers in the store.
    * @param registers, the amount of registers in the store.
    * @param closingTime, the time the store closes.
    */
   public Store(int maxCustomers, int registers, double closingTime)
   {
      // Sets the store as closed.
      isOpen = false;
      this.maxCustomers = maxCustomers;
      this.registers = registers;
      this.closingTime = closingTime;
      this.freeRegisters = registers;
      
      customers = new ArrayList<Customer>();
      registerLine = new FIFO();
      totalCustomersInQueue = 0;
      customerFactory = new CustomerFactory();
   }
   
   /**
    * @return isOpen.
    */
   public boolean getIsOpen()
   {
      return isOpen;
   }
   
   /**
    * Sets isOpen to the argument.
    * @param isOpen
    */
   public void setIsOpen(boolean isOpen)
   {
      this.isOpen = isOpen;
   }
   
   /**
    * @return max allowed customers.
    */
   public int getMaxCustomers()
   {
      return maxCustomers;
   }
   
   /**
    * @return the amount of registers.
    */
   public int getRegisters()
   {
      return registers;
   }
   
   /**
    * @return return the closing time of the store.
    */
   public double getClosingTime()
   {
      return closingTime;
   }
   
   /**
    * @return the coin that the store has made.
    */
   public int getCoinMade()
   {
      return coinMade;
   }
   
   /**
    * @return the amount of free registers.
    */
   public int getFreeRegisters()
   {
      return freeRegisters;
   }
   
   /**
    * @return the time the registers have stood empty.
    */
   public double getRegisterFreeTime()
   {
      return registerFreeTime;
   }
   
   /**
    * @return the time that the customer stood in line.
    */
   public double getCustomerQueueTime()
   {
      return customerQueueTime;
   }
   
   /**
    * @return an instants of the register queue.
    */
   public FIFO getFIFOQueue()
   {
      return registerLine;
   }
   
   /**
    * Creates an new customer.
    * @return new customer.
    */
   public Customer createNewCustomer()
   {
      Customer customer = customerFactory.createCustomer();
      return customer;
   }
   
   /**
    * Adds a customer to the store and gives him a state depending 
    * of the state of the store. If the store is closed the 
    * CustomerState is lateCustomer, else if the store is open 
    * and not full CustomerState is inStore and otherwise the CustomerState
    * is turnedAway.
    * @param customer
    */
   public void addCustomer(Customer customer)
   {
      if(!isOpen)
      {
         customer.setState(CustomerState.lateCustomer);
      }
      else if(getCustomersInStore() < maxCustomers)
      {
         customer.setState(CustomerState.inStore);
      }else
      {
         customer.setState(CustomerState.turnedAway);
      }
      customers.add(customer);
   }
   
   /**
    * Counts the amount of customers with the state inStore.
    * @return number of customers in the store.
    */
   public int getCustomersInStore()
   {
      int customersInStore = 0;
      for(int index = 0; index < customers.size(); index++)
      {
         if(customers.get(index).getState() == CustomerState.inStore)
         {
            customersInStore += 1;
         }
      }
      return customersInStore;
   }
   
   /**
    * Counts the number of customers with the state turnedAway.
    * @return number of customers that was turned away.
    */
   public int getCustomersTurnedAway()
   {
      int customersTurnedAway = 0;
      for(int index = 0; index < customers.size(); index++)
      {
         if(customers.get(index).getState() == CustomerState.turnedAway)
         {
            customersTurnedAway += 1;
         }
      }
      return customersTurnedAway;
   }
   
   /**
    * Counts all the customers that came to the store but not the ones that
    * came after closing (the ones with CusomerState.lateCustomer).
    * @return number of customers that came to the store.
    */
   public int getTotalCustomers()
   {
      int numberOfCustomers = 0;
      for(int index = 0; index < customers.size(); index++)
      {
         if(customers.get(index).getState() != CustomerState.lateCustomer)
         {
            numberOfCustomers += 1;
         }
      }
      return numberOfCustomers;
   }
   
   /**
    * Adds one coin made.
    */
   public void addCoin()
   {
      coinMade += 1;
   }
   
   /**
    * Makes one less free register.
    */
   public void occupieRegister()
   {
      freeRegisters -= 1;
   }
   
   /**
    * Opens one more register.
    */
   public void unoccupieRegister()
   {
      freeRegisters += 1;
   }
   
   /**
    * Removes customer from the store, by giving him the CustomerState
    * notInStore.
    * @param customer
    */
   public void removeCustomer(Customer customer)
   {
      customer.setState(CustomerState.notInStore);
   }
   
   /**
    * Sets the time the registers have stood free.
    * @param time
    */
   void setRegisterFreeTime(double time)
   {
      registerFreeTime = time;
   }
   
   /**
    * Sets the time that the customers have stood in line.
    * @param time
    */
   void setCustomerQueueTime(double time)
   {
      customerQueueTime = time;
   }

   /**
    * @return the totalCustomersInQueue
    */
   public int getTotalCustomersInQueue()
   {
      return totalCustomersInQueue;
   }
   
   /**
    * Adds one more customer to the total number of customers that have
    * stood in the queue.
    */
   public void addTotalCustomersInQueue()
   {
      totalCustomersInQueue += 1;
   }

   /**
    * @return the lastPaymentTime
    */
   public double getLastPaymentTime()
   {
      return lastPaymentTime;
   }

   /**
    * @param lastPaymentTime the lastPaymentTime to set
    */
   public void setLastPaymentTime(double lastPaymentTime)
   {
      this.lastPaymentTime = lastPaymentTime;
   }
   
}
