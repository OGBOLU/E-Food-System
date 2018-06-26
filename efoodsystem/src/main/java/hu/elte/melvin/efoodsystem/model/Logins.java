package hu.elte.melvin.efoodsystem.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "logins")
public class Logins {
    //[Logins] id, loginID, timeIn, timeOut

    @PrimaryKey
    @NonNull
//    @ColumnInfo(name = "word")
    private int id;
    @NonNull
    private String loginID; //for customer = email
    @NonNull
    private String timeIn;
    private String timeOut;
}
