
package one.microstream.microstreamdemo.storage;

import java.util.HashSet;
import java.util.Set;


public class DataRoot
{
	private Set<Customer> customers = new HashSet<>();
	
	public Set<Customer> getCustomers()
	{
		return this.customers;
	}
	
	public void setCustomers(final Set<Customer> customers)
	{
		this.customers = customers;
	}
}
