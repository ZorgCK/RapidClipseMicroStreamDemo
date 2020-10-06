
package one.microstream.microstreamdemo.storage;

public class Customer
{
	private String id;
	private String lastname;
	private String firstname;
	private String mail;
	private String gender;
	private String ipAddress;
	private Double weight;

	public Customer()
	{
		super();
	}

	public Customer(
		final String id,
		final String lastname,
		final String firstname,
		final String mail,
		final String gender,
		final String ipAddress)
	{
		super();
		this.id        = id;
		this.lastname  = lastname;
		this.firstname = firstname;
		this.mail      = mail;
		this.gender    = gender;
		this.ipAddress = ipAddress;
	}
	
	public String getLastname()
	{
		return this.lastname;
	}

	public void setLastname(final String lastname)
	{
		this.lastname = lastname;
	}

	public String getFirstname()
	{
		return this.firstname;
	}

	public void setFirstname(final String firstname)
	{
		this.firstname = firstname;
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(final String id)
	{
		this.id = id;
	}

	public String getMail()
	{
		return this.mail;
	}

	public void setMail(final String mail)
	{
		this.mail = mail;
	}

	public String getGender()
	{
		return this.gender;
	}

	public void setGender(final String gender)
	{
		this.gender = gender;
	}

	public String getIpAddress()
	{
		return this.ipAddress;
	}

	public void setIpAddress(final String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
	
}
