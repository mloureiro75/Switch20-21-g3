package switchtwentytwenty.project.domain.valueobject;

import java.util.Objects;

public class RelationDesignation implements ValueObject {

    private String description;

    public RelationDesignation(String designation) {
        this.description = designation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationDesignation that = (RelationDesignation) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
