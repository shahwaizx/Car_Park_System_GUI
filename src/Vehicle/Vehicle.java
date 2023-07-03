package Vehicle;

import java.io.Serializable;

public class Vehicle implements Serializable {
        private String carName;
        private String numberPlate;
        private double startTime;
        private int slotNo;
        private double bill;
        public Vehicle() {
        }
        public Vehicle(String carName, String numberPlate, double startTime,int slotNo) {
            this.carName = carName;
            this.numberPlate = numberPlate;
            this.startTime = startTime;
            this.slotNo=slotNo;
        }
        public void setCarName(String carName) {
            this.carName = carName;
        }
        public void setNumberPlate(String numberPlate) {
            this.numberPlate = numberPlate;
        }
        public void setStartTime(double startTime) {
            this.startTime = startTime;
        }
        public String getCarName() {
            return carName;
        }
        public String getNumberPlate() {
            return numberPlate;
        }
        public double getStartTime() {
            return startTime;
        }
        public void setSlotNo(int slotNo) {
            this.slotNo = slotNo;
        }
        public int getSlotNo() {
            return slotNo;
        }
        public void setBill(double bill) {
            this.bill = bill;
        }
        public double getBill() {
            return bill;
        }
    }