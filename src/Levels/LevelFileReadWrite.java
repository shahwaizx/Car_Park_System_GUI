package Levels;
import Vehicle.Vehicle;
import personPackage.Person;
import personPackage.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
public class LevelFileReadWrite implements Serializable{
    private static levelWise lw;
    public LevelFileReadWrite(){}
    public LevelFileReadWrite(levelWise lw){
         int c = lw.getCapacity();
         User[]temp = new User[c];
    }
    public static User[] getSlots(){
        return lw.slots;
    }
    public static void writeToFile(levelWise lw,String type){
        try {
            FileOutputStream fo = new FileOutputStream(type +"Slots.ser");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(lw.slots);
            System.out.println("---------OBJECT WRITTEN SUCCESSFULLY----------- ");
            fo.close();
            oo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static User[] LoadArray(User[] arr,String type) {
        File f = new File( type + "Slots.ser");
        try {
            if (!f.exists()) {
                System.out.println("Flag 1 ");
                f.createNewFile();
            } else {
                try {
                    FileInputStream fin = new FileInputStream(f);
                    ObjectInputStream oin = new ObjectInputStream(fin);

                    arr = (User[]) oin.readObject();

                    fin.close();
                    oin.close();
                    return arr;
                }
                catch (EOFException e) {

                }
                catch (Exception ex) {
                    System.out.println("No user found exception in UserWriter");
                }
            }
        } catch (Exception e) {
            System.out.println("File Not Created");
        }
        return null;
    }
    public static void readFromFile(levelWise lw, String type) {
        try {
            FileInputStream fi = new FileInputStream(type + "Slots.ser");
            ObjectInputStream oi = new ObjectInputStream(fi);
            lw.slots = (User[])oi.readObject();
            for (int i = 0;i<lw.getCapacity();i++){
                if (lw.slots[i] != null) {
                    System.out.println(lw.slots[i].getNUMBERPLATE());
                    System.out.println("<---------->");
                }
            }
            System.out.println("DATA READ SUCCESSFULLY");
            fi.close();
            oi.close();
        }
        catch(EOFException e){
            System.out.println("NO DATA IN FILE");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}