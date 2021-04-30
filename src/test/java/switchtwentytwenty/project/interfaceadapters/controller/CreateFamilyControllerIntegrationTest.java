package switchtwentytwenty.project.interfaceadapters.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import switchtwentytwenty.project.datamodel.domainjpa.PersonJPA;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.FamilyDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyIDJPA;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.PersonDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.FamilyJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IFamilyRepositoryJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IPersonRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.family.Family;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.dto.assemblers.implassemblers.FamilyDTODomainAssembler;
import switchtwentytwenty.project.dto.family.InputFamilyDTO;
import switchtwentytwenty.project.dto.person.InputPersonDTO;
import switchtwentytwenty.project.dto.assemblers.implassemblers.PersonDTODomainAssembler;
import switchtwentytwenty.project.interfaceadapters.ImplRepositories.FamilyRepository;
import switchtwentytwenty.project.interfaceadapters.ImplRepositories.PersonRepository;
import switchtwentytwenty.project.interfaceadapters.controller.implcontrollers.CreateFamilyController;
import switchtwentytwenty.project.usecaseservices.applicationservices.implappservices.CreateFamilyService;
import switchtwentytwenty.project.usecaseservices.applicationservices.iappservices.ICreateFamilyService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class CreateFamilyControllerIntegrationTest {

    CreateFamilyController controller;

    // CreateFamilyService
    @Autowired
    ICreateFamilyService createFamilyService;

    @Autowired
    PersonDTODomainAssembler personDTODomainAssembler;

    @Autowired
    FamilyDTODomainAssembler familyDTODomainAssembler;

    // Person Repo
    @Mock
    PersonDataDomainAssembler personDataDomainAssembler;

    @Mock
    IPersonRepositoryJPA iPersonRepositoryJPA;

    @InjectMocks
    PersonRepository personRepository;

    // Family Repo
    @Mock
    FamilyDataDomainAssembler familyDataDomainAssembler;

    @Mock
    IFamilyRepositoryJPA iFamilyRepositoryJPA;

    @InjectMocks
    FamilyRepository familyRepository;


    InputFamilyDTO VALIDCreateFamilyDTO;
    InputPersonDTO inputPersonDTO;

    @BeforeEach
    void setup() {
        // controller = new CreateFamilyController(createFamilyService);
        //VALIDCreateFamilyDTO = new InputFamilyDTO("Silva", "2019/12/12");
        //inputPersonDTO = new InputPersonDTO("email@there.com", "email@here.com", "Rui", "28/12/1990", 123456789, 919999999, "Rua do Coiso", "Porto", "12", "4432-222");
    }


    @DisplayName("Test if a family can be successfully created")
    @Test
    void shouldBeTrueCreateFamily() {

        VALIDCreateFamilyDTO = new InputFamilyDTO("Silva", "2019/12/12");
        inputPersonDTO = new InputPersonDTO("email@here.com", "Rui", "28/12/1990", 123456789, 919999999, "Rua do Coiso", "Porto", "12", "4432-222");

        CreateFamilyService createFamilyService = new CreateFamilyService(personRepository, familyRepository, personDTODomainAssembler, familyDTODomainAssembler);
        //personRepository = new PersonRepository(iPersonRepositoryJPA, personDataDomainAssembler);
        controller = new CreateFamilyController(createFamilyService);
        //familyRepository = new FamilyRepository(iFamilyRepositoryJPA, familyDataDomainAssembler);

        when(iFamilyRepositoryJPA.findById(any(FamilyIDJPA.class))).thenReturn(Optional.empty());
        when(familyDataDomainAssembler.toData(any(Family.class))).thenReturn(new FamilyJPA());
        when(iFamilyRepositoryJPA.save(any(FamilyJPA.class))).thenReturn(new FamilyJPA());
        when(familyDataDomainAssembler.toDomain(any(FamilyJPA.class))).thenReturn(new Family(new FamilyID("email@here.com"), new FamilyName("Silva"), new RegistrationDate("12/12/1999"), new PersonID("email@here.com")));

        when(iPersonRepositoryJPA.findById(any(PersonIDJPA.class))).thenReturn(Optional.empty());
        when(personDataDomainAssembler.toData(any(Person.class))).thenReturn(new PersonJPA());
        when(iPersonRepositoryJPA.save(any(PersonJPA.class))).thenReturn(new PersonJPA());
        when(personDataDomainAssembler.toDomain(any(PersonJPA.class))).thenReturn(new Person(new Name("Rui"), new BirthDate("12/12/1999"), new PersonID("email@here.com"), new VATNumber(123456789), new PhoneNumber(987654321), new Address("Rua", "Covilhã", "6200-000", "1"), new FamilyID("email@here.com")));

        assertTrue(controller.createFamilyAndAdmin(VALIDCreateFamilyDTO, inputPersonDTO));
    }


    @DisplayName("Test if a family isn't created if the admin email is already registered in the app")
    @Test
    void shouldBeFalseCreateFamilyEmailAlreadyregistered() {
        VALIDCreateFamilyDTO = new InputFamilyDTO("Silva", "2019/12/12");
        inputPersonDTO = new InputPersonDTO("email@here.com", "Rui", "28/12/1990", 123456789, 919999999, "Rua do Coiso", "Porto", "12", "4432-222");

        CreateFamilyService createFamilyService = new CreateFamilyService(personRepository, familyRepository, personDTODomainAssembler, familyDTODomainAssembler);
        //personRepository = new PersonRepository(iPersonRepositoryJPA, personDataDomainAssembler);
        controller = new CreateFamilyController(createFamilyService);
        //familyRepository = new FamilyRepository(iFamilyRepositoryJPA, familyDataDomainAssembler);

        when(iFamilyRepositoryJPA.findById(any(FamilyIDJPA.class))).thenReturn(Optional.empty());
        when(familyDataDomainAssembler.toData(any(Family.class))).thenReturn(new FamilyJPA());
        when(iFamilyRepositoryJPA.save(any(FamilyJPA.class))).thenReturn(new FamilyJPA());

        when(iPersonRepositoryJPA.findById(any(PersonIDJPA.class))).thenReturn(Optional.empty()).thenReturn(Optional.of(new PersonJPA()));
        when(personDataDomainAssembler.toData(any(Person.class))).thenReturn(new PersonJPA());
        when(iPersonRepositoryJPA.save(any(PersonJPA.class))).thenReturn(new PersonJPA());

        controller.createFamilyAndAdmin(VALIDCreateFamilyDTO, inputPersonDTO);
        assertFalse(controller.createFamilyAndAdmin(VALIDCreateFamilyDTO, inputPersonDTO));
    }

    @DisplayName("Test if a family isn't created if the family name is null, void or empty")
    @ParameterizedTest
    @ValueSource(strings = {"   "})
    @NullAndEmptySource
    void shouldBeFalseCreateFamilyInvalidFamilyName(String value) {
        InputFamilyDTO INVALIDCreateFamilyDTO = new InputFamilyDTO(value, "2019/12/12");

        CreateFamilyService createFamilyService = new CreateFamilyService(personRepository, familyRepository, personDTODomainAssembler, familyDTODomainAssembler);
        //personRepository = new PersonRepository(iPersonRepositoryJPA, personDataDomainAssembler);
        controller = new CreateFamilyController(createFamilyService);

        assertFalse(controller.createFamilyAndAdmin(INVALIDCreateFamilyDTO, inputPersonDTO));
    }

}