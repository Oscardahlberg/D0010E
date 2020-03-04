package State;

/**
 * The Customer class represents a customer.
 * A customer haves a customer ID and a CustomerState.
 * A customer can have four different states, 
 * inStore, notInStore, turnedAway and lateCustomer.
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */
public class Customer 
{
   private int customerID;
   private CustomerState state;
   
   /**
    * The Customer ID is set when created.
    * @param customerNum
    */
   public Customer(int customerNum)
   {
      this.customerID = customerNum;
   }
   
   /**
    * @return the customer ID
    */
   public int getCustomerID()
   {
      return customerID;
   }
   
   /**
    * @return the customer state.
    */
   public CustomerState getState()
   {
      return state;
   }
   
   /**
    * Sets the state of this customer.
    * @param state
    */
   public void setState(CustomerState state)
   {
      this.state = state;
   }
   
   /**
    * The toSting gives a sting with the customerID.
    */
   public String toString()
   {
      return String.valueOf(customerID);
   }
}

