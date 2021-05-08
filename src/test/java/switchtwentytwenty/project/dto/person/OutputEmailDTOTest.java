package switchtwentytwenty.project.dto.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputEmailDTOTest {

    @Test
    @DisplayName("Should return true if the unpackEmail method is working correctly")
    void unpackEmail() {
        OutputEmailDTO outputEmailDTO = new OutputEmailDTO();
        outputEmailDTO.setEmail("tonyze@gmail.com");

        String expected = "tonyze@gmail.com";
        String result = outputEmailDTO.unpackEmail();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should return true if two identical OutputEmailDTO objects are compared with the equals method")
    void testEquals() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO();
        OutputEmailDTO outputEmailDTOTwo = new OutputEmailDTO();
        outputEmailDTOOne.setEmail("tonyze@gmail.com");
        outputEmailDTOTwo.setEmail("tonyze@gmail.com");

        assertEquals(outputEmailDTOOne, outputEmailDTOTwo);
        assertNotSame(outputEmailDTOOne, outputEmailDTOTwo);
    }

    @Test
    @DisplayName("Should return false if two different OutputEmailDTO objects are compared with the equals method")
    void testEqualsFalse() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO();
        OutputEmailDTO outputEmailDTOTwo = new OutputEmailDTO();
        outputEmailDTOOne.setEmail("tonyze@gmail.com");
        outputEmailDTOTwo.setEmail("tonyze2@gmail.com");

        assertNotEquals(outputEmailDTOOne, outputEmailDTOTwo);
    }

    @Test
    void testEqualsSameOutputEmailDTO() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO();
        outputEmailDTOOne.setEmail("tonyze@gmail.com");
        OutputEmailDTO outputEmailDTOTwo = outputEmailDTOOne;

        assertEquals(outputEmailDTOOne, outputEmailDTOTwo);
    }

    @Test
    void testEqualsDifferentObjects() {
        OutputEmailDTO outputEmailDTO = new OutputEmailDTO();
        outputEmailDTO.setEmailID("tony@gmail.com");
        OutputEmailDTO notOutputEmailDTO = new OutputEmailDTO();
        notOutputEmailDTO.setEmail("not the same");

        assertNotEquals(outputEmailDTO, notOutputEmailDTO);
    }

    @Test
    void testEqualsDifferentFromNull() {
        OutputEmailDTO outputEmailDTO = new OutputEmailDTO();
        outputEmailDTO.setEmail("tonyze@gmail.com");

        assertNotEquals(outputEmailDTO, null);
    }

    @Test
    @DisplayName("Should return true if two identical OutputEmailDTO objects have their hashcodes compared")
    void testHashCode() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO();
        OutputEmailDTO outputEmailDTOTwo = new OutputEmailDTO();
        outputEmailDTOOne.setEmail("tonyze@gmail.com");
        outputEmailDTOTwo.setEmail("tonyze@gmail.com");

        assertNotSame(outputEmailDTOOne,outputEmailDTOTwo);
        assertEquals(outputEmailDTOOne.hashCode(),outputEmailDTOTwo.hashCode());
    }

    @Test
    @DisplayName("Should return false if two different OutputEmailDTO objects have their hashcodes compared")
    void testHashCodeFalse() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO();
        OutputEmailDTO outputEmailDTOTwo = new OutputEmailDTO();
        outputEmailDTOOne.setEmail("tonyze@gmail.com");
        outputEmailDTOTwo.setEmail("tonyze2@gmail.com");

        assertNotEquals(outputEmailDTOOne.hashCode(),outputEmailDTOTwo.hashCode());
    }

    @Test
    void equalsWithNull() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO("tonyze@gmail.com", "1");
        OutputEmailDTO outputEmailDTOTwo = null;

        assertNotEquals(outputEmailDTOOne, outputEmailDTOTwo);
    }

    @Test
    void equalsWithAnotherClass() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO("tonyze@gmail.com", "1");
        String outputEmailDTOTwo = "tonyze@gmail.com";

        assertNotEquals(outputEmailDTOOne, outputEmailDTOTwo);
    }

    @Test
    void getEmailId() {
        OutputEmailDTO outputEmailDTOOne = new OutputEmailDTO("tonyze@gmail.com", "1");

        String expected = "1";

        String result = outputEmailDTOOne.unpackEmailID();

        assertEquals(expected, result);
        assertNotNull(result);
    }
}