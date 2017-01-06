package tiennguyen.sms.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

import tiennguyen.sms.utils.AppConstants;

/**
 * @author NguyenTien
 */
@Table(name = "Contacts")
public class ContactPojo extends Model {

    @Expose
    @Column(name = "name")
    public String name;

    @Expose
    @Column(name = "mobileNumber")
    public String mobileNumber;

    @Expose
    @Column(name = "phoneNumber")
    public String phoneNumber;

    @Expose
    @Column(name = "uri")
    public String uri;

    @Expose
    @Column(name = "type")
    public int type;

    @Expose
    @Column(name = "SmsPojo")
    public SmsPojo smsPojo;

    public ContactPojo(){
        super();
    }


    public ContactPojo(String name, String number){
        super();
        this.name = name;
        this.mobileNumber = number;
    }


    public String chooseToDisplayNameorNumber(){
        if(name.equals(AppConstants.UNKNOWN)==false){
            return name;
        }else{
            return mobileNumber;
        }
    }

    @Override
    public String toString() {
        return this.chooseToDisplayNameorNumber();
    }
}
