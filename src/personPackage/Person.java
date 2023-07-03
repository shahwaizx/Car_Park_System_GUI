package personPackage;

import java.io.Serializable;

public class Person implements Serializable {
    protected String name;
    protected String phoneNo;
    protected String username;
    protected String password;
    protected String CNIC;

    public Person(){

    }


    public Person(String name, String phoneNo, String username, String password,String cnic) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.username = username;
        this.password = password;
        this.CNIC=cnic;
    }

    public Person(String un,String pass){
        this.username = un;
        this.password = pass;
    }
    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public void setName(String n){
        this.name=n;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getName(){
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}


