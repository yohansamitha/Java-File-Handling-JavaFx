package model;

import java.io.Serializable;

public class CustomerDTO implements Serializable {
    private String fileID;
    private String ID;
    private String name;
    private String address;
    private double salary;

    public CustomerDTO(String fileID, String ID, String name, String address, double salary) {
        this.fileID = fileID;
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public CustomerDTO(String ID, String name, String address, double salary) {
        this.setID(ID);
        this.setName(name);
        this.setAddress(address);
        this.setSalary(salary);
    }

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "fileID='" + fileID + '\'' +
                ", ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}
