package Vehicle;

public class SUV extends Vehicle {
    billRates b1;
    private double billSUV;
    public SUV(String cn, String np, double st,int slot) {
        super(cn, np, st,slot);
    }

    public double getRateSUV() {
        return billSUV;
    }


}
