package switchtwentytwenty.project.datamodel;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.datamodel.assemblerjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.assemblerjpa.PersonIDJPA;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AddressJPATest {

    String street = "Rua";
    String city = "Covilândia";
    String zip = "6215-000";
    String doorNumber = "1";

    String id = "emaiil@email.com";
    PersonIDJPA personIDJPA = new PersonIDJPA(id);

    String name = "TonyZe";
    String birthdate = "23/12/1992";
    Integer vat = 999999999;

    FamilyIDJPA familyIDJPA = new FamilyIDJPA(UUID.randomUUID().toString());
    PersonJPA personJPA = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);


    @Test
    void getId() {
        Long expected = Long.valueOf(0);

        AddressJPA addressJPA = new AddressJPA(street, city, zip, doorNumber, personJPA);

        Long result = addressJPA.getId();

        assertEquals(expected, result);
    }

    @Test
    void getStreet() {
        String expected = "Rua";

        AddressJPA addressJPA = new AddressJPA(street, city, zip, doorNumber, personJPA);

        String result = addressJPA.getStreet();

        assertEquals(expected, result);
    }

    @Test
    void getCity() {
        String expected = "Covilândia";

        AddressJPA addressJPA = new AddressJPA(street, city, zip, doorNumber, personJPA);

        String result = addressJPA.getCity();

        assertEquals(expected, result);
    }

    @Test
    void getZipCode() {
        String expected = "6215-000";

        AddressJPA addressJPA = new AddressJPA(street, city, zip, doorNumber, personJPA);

        String result = addressJPA.getZipCode();

        assertEquals(expected, result);
    }

    @Test
    void getDoorNumber() {
        String expected = "1";

        AddressJPA addressJPA = new AddressJPA(street, city, zip, doorNumber, personJPA);

        String result = addressJPA.getDoorNumber();

        assertEquals(expected, result);
    }

    @Test
    void getPerson() {
        String id = "emaiil@email.com";
        PersonIDJPA personIDJPA = new PersonIDJPA(id);

        String name = "TonyZe";
        String birthdate = "23/12/1992";
        Integer vat = 999999999;

        PersonJPA expected = new PersonJPA(personIDJPA, name, birthdate, vat, familyIDJPA);

        AddressJPA addressJPA = new AddressJPA(street, city, zip, doorNumber, personJPA);

        PersonJPA result = addressJPA.getPerson();

        assertEquals(expected, result);
    }


    @Test
    void testEquals() {
        AddressJPA addressJPA = new AddressJPA(street, city, zip, doorNumber, personJPA);

        assertEquals(addressJPA, addressJPA);
    }

    @Test
    void testEqualsTwo() {
        AddressJPA addressJPA = new AddressJPA(street, city, zip, doorNumber, personJPA);

        AddressJPA addressJPATwo = new AddressJPA(street, city, zip, doorNumber, personJPA);

        assertEquals(addressJPA, addressJPATwo);
    }

}