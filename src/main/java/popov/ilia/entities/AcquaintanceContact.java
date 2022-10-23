package popov.ilia.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import popov.ilia.utils.CommonConst;

@Data
@EqualsAndHashCode(callSuper = true)
public class AcquaintanceContact extends Contact {
    public AcquaintanceContact() {
        this.setCategory(CommonConst.ACQUAINTANCE_CATEGORY);
    }
}
