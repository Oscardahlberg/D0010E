package State;

import java.util.ArrayList;

/**
 * 
 * @author Jesper Frisk, jesfri-8
 *
 */
public class Store 
{
   private boolean isOpen;
   private int maxCustomers;
   private int registers;
   private double closingTime;
   
   private ArrayList<Customer> customers;
   private CustomerFactory customerFactory;
   private FIFO registerLine;
   
//   private int customersInStore;
//   private int customersTurnedAway;
   private int coinMade;
   private int freeRegisters;
   
   private double registerFreeTime;
   private double customerQueueTime;
   private int totalCustomersInQueue;
   
   public Store(int maxCustomers, int registers, double closingTime)
   {
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
   
   public boolean getIsOpen()
   {
      return isOpen;
   }
   
   public void setIsOpen(boolean isOpen)
   {
      this.isOpen = isOpen;
   }
   
   public int getMaxCustomers()
   {
      return maxCustomers;
   }
   
   public int getRegisters()
   {
      return registers;
   }
   
   public double getClosingTime()
   {
      return closingTime;
   }
   
   public int getCoinMade()
   {
      return coinMade;
   }
   
   public int getFreeRegisters()
   {
      return freeRegisters;
   }
   
   public double getRegisterFreeTime()
   {
      return registerFreeTime;
   }
   
   public double getCustomerQueueTime()
   {
      return customerQueueTime;
   }
   
   public FIFO getFIFOQueue()
   {
      return registerLine;
   }
   
   public Customer createNewCustomer()
   {
      Customer customer = customerFactory.createCustomer();
      return customer;
   }
   
   public void addCustomer(Customer customer)
   {
      if(!isOpen)
      {
         customer.setState(CustomerState.notInStore);
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
   
   public int getTotalCustomers()
   {
      return customers.size();
   }
   
   public void addCoin()
   {
      coinMade += 1;
   }
   
   public void occupieRegister()
   {
      freeRegisters -= 1;
   }
   
   public void unoccupieRegister()
   {
      freeRegisters += 1;
   }
   
   public void removeCustomer(Customer customer)
   {
      customer.setState(CustomerState.notInStore);
//      customersInStore -= 1;
   }
   
   void setRegisterFreeTime(double time)
   {
      registerFreeTime = time;
   }
   
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
   
   public void addTotalCustomersInQueue()
   {
      totalCustomersInQueue += 1;
   }
   
}
