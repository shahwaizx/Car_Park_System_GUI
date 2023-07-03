package Vehicle;
public class billRates{
    private static double suvBill=0.05;
    private static double carBill=0.03;
    private static double bikeBill=0.01;
    public static void setBikeBill(double bikeBill) {
        billRates.bikeBill = bikeBill;
    }
    public static void setCarBill(double carBill){
        billRates.carBill=carBill;
    }
    public static void setSuvBill(double suvBill) {
        billRates.suvBill=suvBill;
    }
    public static double getBikeBill() {
        return bikeBill;
    }
    public static double getCarBill() {
        return carBill;
    }
    public static double getSuvBill() {
        return suvBill;
    }
}