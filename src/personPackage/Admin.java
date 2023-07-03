package personPackage;

import Levels.levelWise;
import Vehicle.*;
public class Admin extends Person{
    levelWise l1;
    public Admin()
    {
    }
    public Admin(String name,String phoneNo,String un,String pass,String cnic){
        super(name,phoneNo,un,pass,cnic);
    }
    public Admin(String name,String phoneNo,String un,String pass,String cnic,levelWise l){
        super(name,phoneNo,un,pass,cnic);
        this.l1=l;

    }
    public void setSUVBill(double newRate) {
        billRates.setSuvBill(newRate);
    }
    public void setBikeBill(double newRate){
        billRates.setBikeBill(newRate);
    }
    public void setCarBill(double newRate){
        billRates.setCarBill(newRate);
    }
    public void exitVehicle(String numberplate){
        l1.exitVehicle(numberplate);
    }
}