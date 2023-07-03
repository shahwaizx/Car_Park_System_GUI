package personPackage;

import Vehicle.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class personFileReadWrite implements Serializable {
    private static ArrayList<Person> arrayList;
    public personFileReadWrite() {
    }

    public personFileReadWrite(Person person) {
        if (person instanceof Admin) {
            LoadArrayListPerson("admin");
        } else if (person instanceof User) {
            LoadArrayListPerson("user");
        }
    }

    public static ArrayList<Person> getArrayList() {
        return arrayList;
    }

    public static void LoadArrayListPerson(String type) {
        File f = new File(type + "Data.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            } else {
                try {
                    FileInputStream fin = new FileInputStream(f);
                    ObjectInputStream oin = new ObjectInputStream(fin);

                    arrayList = (ArrayList<Person>) oin.readObject();

                    fin.close();
                    oin.close();
                } catch (EOFException e) {

                } catch (Exception ex) {
                    System.out.println("No user found exception in UserWriter");
                }
            }
        } catch (Exception e) {
            System.out.println("File Not Created");
        }
    }

    public static void writeToFile(ArrayList<Person> arrayList, String type) {
        try {
            FileOutputStream fo = new FileOutputStream(type + "Data.ser");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(arrayList);
            System.out.println("---------OBJECT WRITTEN SUCCESSFULLY----------- ");
            fo.close();
            oo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void readFromFile(String type) {
        try {
            FileInputStream fi = new FileInputStream(type + "Data.ser");
            ObjectInputStream oi = new ObjectInputStream(fi);
            arrayList = (ArrayList<Person>) oi.readObject();
            for (Person p : arrayList) {
                System.out.println(p.getName());
                System.out.println(p.username);
                System.out.println(p.password);
                System.out.println("END OF PERSON");
            }
            fi.close();
            oi.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Admin ad = new Admin("Admin", "03215864765", "admin123", "123456", "12345");

        ArrayList<Person> adminTemp = new ArrayList<>();
        adminTemp.add(ad);

        if (adminTemp == null) {
            adminTemp = new ArrayList<>();
        }
        personFileReadWrite.writeToFile(adminTemp, "admin");
        personFileReadWrite.readFromFile("admin");
    }
}

