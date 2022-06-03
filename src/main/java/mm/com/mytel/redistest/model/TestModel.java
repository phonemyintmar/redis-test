package mm.com.mytel.redistest.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "X2TXN_BONUS_TRANSACTION")
public class TestModel {
    @Id
    private String id;

    @Column( name = "FROM_PHONE_ON_SECOND_TXN")
    private String phoneNumber;

    @Column( name = "LATEST_THIRD_PARTY_LOG_ID")
    private String logId;
}
