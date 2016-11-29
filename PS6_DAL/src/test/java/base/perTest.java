package base;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class perTest {
	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();			
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);
		
	}

	@After
	public void tearDown() throws Exception{
		PersonDomainModel per = PersonDAL.getPerson(person1.getPersonID());
		PersonDAL.deletePerson(person1.getPersonID());
		
		assertNull("The Person should not be in the data", per);
	}
	
	@Test
	public void AddPersonTest()
	{
		PersonDomainModel per;
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This person should not be in the data",per);
		PersonDAL.addPerson(person1);
		
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNotNull("added", per);
	}

	@Test
	public void updatePersonTest(){
		PersonDomainModel per = null;
		final String LASTNAME = "Liu";
		
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("The Person is not in the data",per);
		PersonDAL.addPerson(person1);
		
		person1.setLastName(LASTNAME);
		PersonDAL.update(person1);
		
		per = PersonDAL.getPerson(person1.getPersonID());
		
		assertTrue("Name not change",person1.getLastName()==LASTNAME);
		
		
		
	}
	@Test
	public void DeletePersonTest()
	{
		PersonDomainModel per;
		
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This Person is not in the data",per);
		
		PersonDAL.addPerson(person1);
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("found",per);

		PersonDAL.deletePerson(person1.getPersonID());
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("This is person have been deleted",per);
	}


}
