package Vehicle;

public class Bike extends Vehicle {
    billRates b2;
    private double billBike;

    public Bike(String cn, String np, double st,int slotNo) {
        super(cn, np, st,slotNo);
    }

    public double getBillBike() {
        return billBike;
    }
}
