package State;

public class Customer 
{
   private int customerID;
   private CustomerState state;
   
   public Customer(int customerNum)
   {
      this.customerID = customerNum;
   }
   
   public int getCustomerID()
   {
      return customerID;
   }
   
   public CustomerState getState()
   {
      return state;
   }
   
   public void setState(CustomerState state)
   {
      this.state = state;
   }
   
   public String toString()
   {
      return String.valueOf(customerID);
   }
}
