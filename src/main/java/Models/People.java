package Models;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private String position;
    private Date dBirsday;
    private String call;
    private String image;

    /*
       private String call;
       private String posada;  //Prtod Inli
    */
    
    public boolean birsDay( Date date){
        String a = GeneratorDuty.dateFormat.format(getdBirsday()).substring(0,5);
        String b = GeneratorDuty.dateFormat.format(date).substring(0,5);
        if(a.equals(b)){
            System.out.println(getName()+" "+getSoname()+" dnuha ne pidot v naryd<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            return false;
        }else {
            return true;
        }
    }
    
    public boolean dontVacatoin(Date date){ //c filter na otpusk rabotaet
        for (int i = 0; i <listVakation.size() ; i++) {
            if(date.getTime()>= listVakation.get(i).getFirstData().getTime() && date.getTime() <= listVakation.get(i).getLastData().getTime()){
                System.out.println(getName()+"  "+getSoname()+"  u nego otpusk ne pidot");
                return false;
            }
        }
        return true;
    }

    public boolean dontTooDay(Date date){

        for (int i = 0; i <listDuti.size() ; i++) {
            Calendar c = Calendar.getInstance(); // potomn smotri
            if(listDuti.get(i).getDate().getTime() >= date.getTime()) {
                System.out.println(GeneratorDuty.dateFormat.format(date) + "  "+ GeneratorDuty.dateFormat.format(listDuti.get(i).getDate()));
                c.setTime(listDuti.get(i).getDate()); // devn naradu  v zade;  // otnyt 2 dny i sravnyt
                c.add(Calendar.DATE ,-2);  // nenada ato y snachala stavlu i sled narydu za mesec budu udalyt
//                if(c.getTime().getTime() <= date.getTime()){
//                    System.out.println("+_+ u nego nedavobull narad ne podoidot"  +name+"  "+soname);
//                    return false;
//                }
            }

            if(listDuti.get(i).getDate().getTime() <= date.getTime()){
                c.setTime(listDuti.get(i).getDate());// den naraydy speredu; // nakinut 2 dny i proverit ravenstvo
                c.add(Calendar.DATE ,2);
                System.out.println("----");
                if(c.getTime().getTime() >= date.getTime()){
                    System.out.println("+-+ u nego naryd on ne podoidot" + name+"  "+fname);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean removeDutiMansPerson(Mans mans){ // удаляет в самом чуваку нада провырити чи видалить в головній колекції
      //  FakeRepositori.fakeDuty.removeAll((this.getListDuti().stream().filter(duty -> duty.getDate().getMonth()+1 ==  mans.getCountmans()).collect(Collectors.toList())));
        this.listDuti.removeAll((this.getListDuti().stream().filter(duty -> duty.getDate().getMonth()+1 ==  mans.getCountmans()).collect(Collectors.toList())));
        System.out.println("Проверка длени "+this.getListDuti().stream().filter(duty -> duty.getDate().getMonth()+1 ==  mans.getCountmans()).collect(Collectors.toList()).size());
        return true;
    }

    public boolean removeDeyDutyPerson(Date date){
        this.listDuti.removeAll(
                this.listDuti.stream()
                        .filter(p->(GeneratorDuty.dateFormat.format(date).substring(0,5)
                        .equals(GeneratorDuty.dateFormat.format(p.getDate()).substring(0,5)))).collect(Collectors.toList())
        );
        return true;
    }

    public boolean chesked(int a){
        System.out.println( a+"  "+this.getName() + "  "+this.getSoname()+" ");
        return true;
    }

    public People(){
        this.password = "";
        this.login = "";
        this.name = new SimpleStringProperty("");
        this.soname= new SimpleStringProperty("") ;
        this.fname= new SimpleStringProperty("");
        this.rang= new SimpleStringProperty("");
        this.massenger = new Massenger();
        this.call = "";
        this.image ="";
        this.dBirsday = new Date();
    }

    public boolean getDeyBirsday(){
        return true;
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
                ", name=" + name +
                ", soname=" + soname +
                ", fname=" + fname +
                ", rang=" + rang +
                ", listDuti=" + listDuti +
                ", listVakation=" + listVakation +
                ", massenger=" + massenger +
                ", position='" + position + '\'' +
                ", dBirsday=" + GeneratorDuty.dateFormat.format(dBirsday)  +
                ", call='" + call + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String toString2(){
        return  rang.getValue()+" "+name.getValue()+" "+soname.getValue()+" "+fname.getValue();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Massenger getMassenger() {
        return massenger;
    }

    public void setMassenger(Massenger massenger) {
        this.massenger = massenger;
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

    public Date getdBirsday() {
        return dBirsday;
    }

    public void setdBirsday(Date dBirsday) {
        this.dBirsday = dBirsday;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
