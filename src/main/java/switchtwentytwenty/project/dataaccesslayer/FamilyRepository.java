package switchtwentytwenty.project.dataaccesslayer;

import switchtwentytwenty.project.Repository;
import switchtwentytwenty.project.domain.family.Family;
import switchtwentytwenty.project.shared.EmailAddress;
import switchtwentytwenty.project.shared.FamilyID;
import switchtwentytwenty.project.shared.FamilyName;
import switchtwentytwenty.project.shared.RegistrationDate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FamilyRepository implements Repository<Family> {

    private final List<Family> families;
    //private final Families families = new Families();

    public FamilyRepository() {
        this.families = new ArrayList<>();
    }

    public void addFamily(Family family) {
        this.families.add(family);
    }

    // public Family findFamilyByID(FamilyID id){

    //}

    public void createAndAddFamily(FamilyName familyName, FamilyID familyID, RegistrationDate registrationDate, EmailAddress adminEmail) {
        Family family = new Family(familyID, familyName, registrationDate, adminEmail);
        this.families.add(family);
    }

    public FamilyID generateAndGetFamilyID() {
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        if (checkIfFamilyIDExists(familyID)) {
            familyID = generateAndGetFamilyID();
        }
        return familyID;
    }

    private boolean checkIfFamilyIDExists(FamilyID familyID) {
        boolean result = false;
        for (Family family : this.families) {
            if (family.isIDofThisFamily(familyID)) {
                result = true;
            }
        }
        return result;
    }

    public void removeFamily(FamilyID familyID) {
        Family family = getFamilyByID(familyID);
        if (family != null) {
            this.families.remove(family);
        }
    }

    private Family getFamilyByID(FamilyID familyID) {
        Family targetFamily = null;
        for (Family family : this.families) {
            if (family.isIDofThisFamily(familyID)) {
                targetFamily = family;
            }
        }
        return targetFamily;
    }

    /*
    v1
    EmailAddress;
    new Family(DTO, EmailAddress);
    new Admin(DTO, EmailAddress, idfamily);
    verifyIfPresent;
    add Admin to repo;
    add family to repo;

    v2
    EmailAddress;
    verifyIFPresent;
    new Admin(DTO1,email);
    add Admin;
    new Family(DTO2,email);
    add Family; or remove Admin;
    set familyID in Admin;
    */

 /*   private class Families implements Iterable<Family>{
        private final List<Family> families;

        private Families(){
            this.families = new ArrayList<>();
        }

        public Iterator<Family> iterator(){
            return this.families.iterator();
        }
    }*/
}

