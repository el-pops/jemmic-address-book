package popov.ilia.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import popov.ilia.utils.CommonConst;

@Data
@EqualsAndHashCode(callSuper = true)
public class FriendContact extends Contact {

    private int yearsOfFriendship;

    public FriendContact() {
        this.setCategory(CommonConst.FRIEND_CATEGORY);
    }

    @Override
    public String toString(){
        return String.format("%-16s %s %-16s","Friend", super.toString(), getYearsOfFriendship());
    }

    @Override
    public String getFullFormatedPersonInfo(){
        return super.getFullFormatedPersonInfo()+String.format("%-16s", getYearsOfFriendship());
    }

}
