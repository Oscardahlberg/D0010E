package State;

/**
 * An instants of this class can create a customer.
 * @author Jesper Frisk, Shahriar Chegini, Oscar Dahlberg, Folke Forshed.
 *
 */
public class CustomerFactory
{
   // Ceeps track of the number of customers created so everyone gets an unic
   // customer ID.
   private int customersCreated = 0;
   
   /**
    * Creates a new customer.
    * @return customer.
    */
   public Customer createCustomer()
   {
      Customer customer = new Customer(customersCreated);
      customersCreated += 1;
      return customer;
   }
}
