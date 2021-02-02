package switchtwentytwenty.project.domain.dtos.output;

import java.util.Objects;

public class FamilyWithoutAdministratorDTO {
    private String familyName;
    private int familyID;

    /**
     *
     * @param familyName
     * @param familyID
     */

    public FamilyWithoutAdministratorDTO (String familyName, int familyID) {
        this.familyName = familyName;
        this.familyID = familyID;
    }

    @Override
    public boolean equals (Object o){
        if (this == o) return true;

        if (!(o instanceof FamilyWithoutAdministratorDTO)) return false;

        FamilyWithoutAdministratorDTO otherDTO = (FamilyWithoutAdministratorDTO) o;

        return this.familyName.equals(otherDTO.familyName) && this.familyID == otherDTO.familyID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyName, familyID);
    }
}
