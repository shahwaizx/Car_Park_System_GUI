package Vehicle;
public class Car extends Vehicle {
    private double billCar;

    public Car(String cn, String np, double st,int slotNo) {
        super(cn, np, st,slotNo);
    }
    public double getRateCar() {
        return billCar;
    }
}