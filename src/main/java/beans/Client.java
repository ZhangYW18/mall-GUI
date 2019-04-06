package beans;

import java.io.IOException;

public class Client {
    private int id;
    private String name;
    private boolean sex;
    private String address;
    private String phone;
    private String email;
    private String company;

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean getSex(){
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone)  {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "(" +
                 id +
                ", \'" + name +
                "\', \'" + (sex?1:0) +
                "\', \'" + address +
                "\', \'" + phone +
                "\', \'" + email +
                "\', \'" + company + "\')";
    }
}
