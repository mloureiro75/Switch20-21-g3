package switchtwentytwenty.project.shared;

import java.util.Objects;
import java.util.UUID;


public class FamilyID implements ID<UUID> {

    private final UUID familyID;

    public FamilyID(UUID familyID) {
        this.familyID = familyID;
        validateID();
    }

    public FamilyID clone(){
        FamilyID clone = new FamilyID(this.familyID);
        return clone;
    }

    /**
     * Method that validates a familyID, throws an exception if the ID isn't valid
     */
    private void validateID() {
        if (!isIDValid()) {
            throw new IllegalArgumentException("Invalid ID");
        }
    }

    /**
     * Method to determine if an ID is valid, i.e. not null
     *
     * @return boolean, true if ID is valid, false otherwise
     */
    private boolean isIDValid() {
        return this.familyID != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamilyID)) return false;
        FamilyID familyID1 = (FamilyID) o;
        return familyID.equals(familyID1.familyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyID);
    }

}