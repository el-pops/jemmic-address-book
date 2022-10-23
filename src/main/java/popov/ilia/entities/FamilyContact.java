package popov.ilia.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import popov.ilia.utils.CommonConst;

@Data
@EqualsAndHashCode(callSuper = true)
public class FamilyContact extends Contact {

    private String relation;
    public FamilyContact() {
        this.setCategory(CommonConst.FAMILY_CATEGORY);
    }

    @Override
    public String getFullFormatedPersonInfo(){
        return super.getFullFormatedPersonInfo()+String.format("%-16s", getRelation());
    }
}
