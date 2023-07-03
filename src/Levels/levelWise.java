package Levels;
import Vehicle.*;
import personPackage.*;
public class levelWise {
    private int capacity;
    public User[] slots;
    billRates b;

    public levelWise() {
    }

    public levelWise(int capacity) {
        this.capacity = capacity;
        slots = new User[capacity];
        b=new billRates();
    }
    public void setSlots(User[] arr){
        this.slots = arr;
    }
    public User[] getSlots(){
        return slots;
    }
    public boolean hasAvailableSlot() {
        for (User user : slots) {
            if (user == null) {
                return true;
            }
        }
        return false;
    }
    public boolean alreadyParked(int slot){
       return  slots[slot] != null;
    }

    public void parkVehicle(User user) {
        int s = user.SLOTNO();
        if(slots[s] == null) {
            slots[s] = user;
            System.out.println("Parked successfully");

        }
        else{
            System.out.println("Car is already parked");
        }
        }
        public void EXITVEHICLEADMIN(int slotnumber){
        slots[slotnumber] = null;
        }

    public double exitVehicle(String numberPlate){
        boolean check=false;
        double bill = 0;
        for (int i = 0; i < capacity;i++){
            if (slots[i]!=null && slots[i].getNUMBERPLATE().equalsIgnoreCase(numberPlate)){
                System.out.println("Name " + slots[i].getName());
                System.out.println("Number plate "+slots[i].getNUMBERPLATE());
                double end=System.currentTimeMillis()/1000.0;

                if(slots[i].returnVehicleObject() instanceof SUV){
                    bill=(end-slots[i].STARTTIME())*b.getSuvBill();
                }
                else if (slots[i].returnVehicleObject() instanceof Bike) {
                    bill=(end-slots[i].STARTTIME())*b.getBikeBill();
                }
                else if(slots[i].returnVehicleObject() instanceof Car){
                    bill=(end-slots[i].STARTTIME())*b.getCarBill();
                }
                System.out.println("bill: "+bill);
                slots[i] = null;
                check=true;
                break;
            }
        }
        if(check){
            System.out.println("Vehicle Exited!!!");


        }
        else{
            System.out.println("Vehicle not found");
        }
        return bill;

    }
    public int getAvailableSlots() {
        int availableSlots = 0;
        for (User user : slots) {
            if (user == null) {
                availableSlots++;
            }
        }
        return availableSlots;
    }
    public void displayAvailableSlots(){
        for(int i=0;i<capacity;i++){
            if(slots[i]==null){
                System.out.println("Slot "+ i);
            }
            else{
                System.out.println("");
            }
        }
    }
    public boolean correctSlot(int slot){
        return slot>=0 && slot<=capacity-1;
    }
    public int getCapacity(){
        return capacity;
    }
    public void displayParkedVehicles(){
        for(int i=0;i<capacity;i++){
            if(slots[i]!=null){
                System.out.println("Slot "+i);
            }
        }
    }
}