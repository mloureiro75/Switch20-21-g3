package switchtwentytwenty.project.interfaceadapters.implrepositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import switchtwentytwenty.project.datamodel.assemblerjpa.implassemblersjpa.PersonDataDomainAssembler;
import switchtwentytwenty.project.datamodel.domainjpa.PersonIDJPA;
import switchtwentytwenty.project.datamodel.domainjpa.PersonJPA;
import switchtwentytwenty.project.datamodel.repositoryjpa.IPersonRepositoryJPA;
import switchtwentytwenty.project.domain.aggregates.person.Person;
import switchtwentytwenty.project.domain.valueobject.*;
import switchtwentytwenty.project.exceptions.EmailAlreadyRegisteredException;
import switchtwentytwenty.project.exceptions.PersonAlreadyRegisteredException;
import switchtwentytwenty.project.usecaseservices.irepositories.IPersonRepository;

import java.util.*;

@Repository
public class PersonRepository implements IPersonRepository {

    private IPersonRepositoryJPA personRepositoryJPA;

    private PersonDataDomainAssembler personAssembler;

    @Autowired
    public PersonRepository(IPersonRepositoryJPA iPersonRepositoryJPA, PersonDataDomainAssembler personDataDomainAssembler) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.personRepositoryJPA = iPersonRepositoryJPA;
        this.personAssembler = personDataDomainAssembler;

    }


    /**
     * Method to check if a PersonID is already registered in the database
     * Optional says yes or no when you ask something. In this case it says if there is something in the personRepositoryJPA.
     * After you can obtain that something with optional.get().
     *
     * @param personID PersonID to check if it's already present in the database
     * @return true if there is already a person registered with that PersonID
     */
    @Override
    public boolean isPersonIDAlreadyRegistered(PersonID personID) {
        boolean emailIsRegistered = false;
        Optional<PersonJPA> optional = personRepositoryJPA.findById(new PersonIDJPA(personID.toString()));
        if (optional.isPresent()) {
            emailIsRegistered = true;
        }
        return emailIsRegistered;
    }


    @Override
    public Person getByID(PersonID email) {
        return retrievePersonFromRepository(email);
    }

    private Person retrievePersonFromRepository(PersonID id) {
        PersonIDJPA personIDJPA = new PersonIDJPA(id.toString());
        Optional<PersonJPA> personJPA = personRepositoryJPA.findById(personIDJPA);
        Person person = personAssembler.toDomain(personJPA.get());
        return person;
    }



    /**
     * Method to add the inputted Person domain object into the repository.
     * The Person domain object will be converted into a PersonJPA data object and sent to the repository.
     *
     * @param person domain object we want to add to the person repository
     */
    @Override
    public Person add(Person person) {
        PersonJPA registeredPersonJPA;
        Person registeredPerson;
        if (!isPersonIDAlreadyRegistered(person.id())) {
            PersonJPA personJPA = personAssembler.toData(person);
            registeredPersonJPA = personRepositoryJPA.save(personJPA);
            registeredPerson = personAssembler.toDomain(registeredPersonJPA);
        } else {
            throw new PersonAlreadyRegisteredException("Person is already registered in the database");
        }
        return registeredPerson;
    }

    @Override
    public Person updatePerson(Person person) {
        PersonJPA personJPA = personAssembler.toData(person);
        PersonJPA updatedPersonJPA = personRepositoryJPA.save(personJPA);
        Person updatedPerson = personAssembler.toDomain(updatedPersonJPA);
        return updatedPerson;

    }

}