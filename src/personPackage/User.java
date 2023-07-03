package personPackage;
import Vehicle.*;

import java.io.Serializable;

public class User extends Person {
    Vehicle vehicle;
    public User() {

    }
    public User(String name, String phoneNo, String username, String password,String cnic){
        super(name, phoneNo, username, password,cnic);
    }
    public User(String name, String phoneNo, String username, String password,String cnic,Vehicle vehicle) {
        super(name, phoneNo, username, password,cnic);
        this.vehicle = vehicle;
    }
    public String getNUMBERPLATE(){
         return vehicle.getNumberPlate();
    }
    public int SLOTNO(){
        return vehicle.getSlotNo();
    }
    public double STARTTIME(){
        return vehicle.getStartTime();
    }
    public Vehicle returnVehicleObject(){
        return vehicle;
    }
}