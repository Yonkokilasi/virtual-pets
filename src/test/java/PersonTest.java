import org.junit.*;
import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void person_instantiatesCorrectly_true() {
        Person testPerson = new Person("Henry", "[email protected]");
        assertEquals(true, testPerson instanceof Person);
    }
    @Test
    public void getName_personInstantiatesWithName_Henry() {
        Person testPerson = new Person("Henry", "[email protected]");
        assertEquals("Henry", testPerson.getName());
    }
    @Test
    public void getEmail_personInstantiatesWithEmail_String() {
        Person testPerson = new Person("Henry","[email protected]");
        assertEquals("[email protected]", testPerson.getEmail());
    }
    @Test
    public void equals_returnsTrueIfNameAndEmailAreSame_true() {
        Person firstPerson = new Person("Henry","[email protected]");
        Person anotherPerson = new Person("Henry", "[email protected]");
        assertTrue(firstPerson.equals(anotherPerson));
    }


}
