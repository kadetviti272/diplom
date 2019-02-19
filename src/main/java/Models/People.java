package Models;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class People {
    private int id;
    private String password;
    private String login;
    private SimpleStringProperty name;
    private SimpleStringProperty soname;
    private SimpleStringProperty fname;
    private SimpleStringProperty rang;
    private List<Duty> listDuti= new ArrayList<Duty>();
    private List<Vacation> listVakation=new ArrayList<Vacation>();
    private Massenger massenger;

    public People(){
        this.password = "Net tagogo";
        this.login = "Net takogo";
        this.name = new SimpleStringProperty("Net tagogo");
        this.soname= new SimpleStringProperty("Net tagogo") ;
        this.fname= new SimpleStringProperty("Net tagogo");
        this.rang= new SimpleStringProperty("Net tagogo");
    }

    public People(int id, String login, String password, String name, String soname, String fname, String rang, Massenger massenger ) {
        this.id = id;
        this.password = password;
        this.login = login;
        this.name = new SimpleStringProperty(name);
        this.soname= new SimpleStringProperty(soname) ;
        this.fname= new SimpleStringProperty(fname);
        this.rang= new SimpleStringProperty(rang);
        this.massenger = massenger;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", name=" + name.getValue() +
                ", soname=" + soname.getValue() +
                ", fname=" + fname.getValue() +
                ", rang=" + rang.getValue() +
                ", listDuti=" + listDuti +
                ", listVakation=" + listVakation +
                '}';
    }

    public String toString2(){
        return  rang.getValue()+" "+name.getValue()+" "+soname.getValue()+" "+fname.getValue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSoname() {
        return soname.get();
    }

    public SimpleStringProperty sonameProperty() {
        return soname;
    }

    public void setSoname(String soname) {
        this.soname.set(soname);
    }

    public String getFname() {
        return fname.get();
    }

    public SimpleStringProperty fnameProperty() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public String getRang() {
        return rang.get();
    }

    public SimpleStringProperty rangProperty() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang.set(rang);
    }

    public List<Duty> getListDuti() {
        return listDuti;
    }

    public void setListDuti(List<Duty> listDuti) {
        this.listDuti = listDuti;
    }

    public List<Vacation> getListVakation() {
        return listVakation;
    }

    public void setListVakation(List<Vacation> listVakation) {
        this.listVakation = listVakation;
    }

}
