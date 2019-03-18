package Models;


import Controllers.admin.RedactPerson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.healthmarketscience.jackcess.*;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

public class FakeRepositori {

    public static SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
    public static ObservableList<Duty> fakeDuty = FXCollections.observableArrayList();
    public static ObservableList<Vacation> fakeVacation = FXCollections.observableArrayList();
    public static ObservableList<People> fakePeople = FXCollections.observableArrayList();
    public static Object[] arrControler=new Object[2];
    public static int idPersonAutoriz;
    public static Type itemsMapType = new TypeToken<List<Masage>>() {}.getType();
    public static People autorizadPeopl;
    public static boolean fclin = false;
    public static Massenger chaffMasanger = new Massenger();
    //public static List<Masage> chaffMasanger = new ArrayList<>();

    static {
        try {
            ReadDB();
//            clinDb();
//            wraitDb();

        } catch (IOException e) {
            e.printStackTrace();
        }
        binding();
        arrControler[0]=new RedactPerson();
        System.out.println("++++++++++++");
    }

    public static void ReadDB() throws IOException {
        try (Database db = DatabaseBuilder.open(new File("db.mdb"))){
            Table table = db.getTable("user");
            for (Row row : table) {
                People tempPeople = new People();
                Massenger tempMasanger = new Massenger();
                for (Column column : table.getColumns()) {
                    String columnName = column.getName();
                    switch (columnName) {
                        case "id":
                            tempPeople.setId((Integer) row.get(columnName));
                            break;
                        case "password":
                            tempPeople.setPassword((String) row.get(columnName));
                            break;
                        case "login":
                            tempPeople.setLogin((String) row.get(columnName));
                            break;
                        case "name":
                            tempPeople.setName((String) row.get(columnName));
                            break;
                        case "soname":
                            tempPeople.setSoname((String) row.get(columnName));
                            break;
                        case "fname":
                            tempPeople.setFname((String) row.get(columnName));
                            break;
                        case "rang":
                            tempPeople.setRang((String) row.get(columnName));
                            break;
                        case "text":
                            System.out.println((String)row.get(columnName));
                            tempMasanger.setMasageHistory(new Gson().fromJson((String)row.get(columnName),itemsMapType));
                          //  System.out.println(tempMasanger.getText());
                            break;
                        case  "inmasage":
                            tempMasanger.setIncoming(((Boolean) row.get(columnName)).booleanValue());
                            break;
                        case  "outmasage":
                            tempMasanger.setOutcoming(((Boolean) row.get(columnName)).booleanValue());
                            break;
                        case  "call":
                            tempPeople.setCall((String) row.get(columnName));
                            break;
                        case "dBirsday":
                            tempPeople.setdBirsday((Date) row.get(columnName));
                            break;
                        case "image":
                            tempPeople.setImage((String) row.get(columnName));
                            break;
                        case "position":
                            System.out.println("----------------------------------");
                            tempPeople.setPosition((String) row.get(columnName));
                            break;
                    }
                }
                tempPeople.setMassenger(tempMasanger);
                fakePeople.add(tempPeople);
            }

            // metod 2;
            table = db.getTable("duty");
            System.out.println("-----------------------------------------------");
            for (Row row : table) {
                Duty tempduty = new Duty();
                for (Column column : table.getColumns()) {
                    String columnName = column.getName();
                    Object value = row.get(columnName);
                    System.out.println(columnName);

                    switch (columnName) {
                        case "dey":
                            tempduty.setData((Date) row.get(columnName));
                            break;
                        case "fkuser":
                            tempduty.setId((Integer) row.get(columnName));
                            break;
                        case  "certified" :
                            tempduty.setCertified(((Boolean) row.get(columnName)).booleanValue());
                            break;
                    }
                }
                fakeDuty.add(tempduty);
            }

            // METOD 3
            table = db.getTable("vacation");
            for (Row row : table) {
                Vacation tempvacation = new Vacation();
                for (Column column : table.getColumns()) {
                    String columnName = column.getName();
                    System.out.println(columnName+"0000000000000000");
                    Object value = row.get(columnName);
                    switch (columnName) {
                        case "fk_user":
                            tempvacation.setId((Integer) row.get(columnName));
                            break;
                        case "firstdata":
                            tempvacation.setFirstData((Date) row.get(columnName));
                            break;
                        case "lastdata":
                            tempvacation.setLastData((Date) row.get(columnName));
                            break;
                    }
                }

                fakeVacation.add(tempvacation);
            }
        }

    }  // zcitovanie z DB i peretovoryet v kolekciu

    public static void binding(){
        for (int i = 0; i <fakePeople.size(); i++) {
            for (int j = 0; j <fakeVacation.size(); j++) {
                if(fakePeople.get(i).getId()==fakeVacation.get(j).getId()){
                    fakePeople.get(i).getListVakation().add(fakeVacation.get(j));
                    fakeVacation.get(j).setPeople(fakePeople.get(j));
                }
            }

            for (int j = 0; j <fakeDuty.size() ; j++) {
                if(fakePeople.get(i).getId()==fakeDuty.get(j).getId()){
                    fakePeople.get(i).getListDuti().add(fakeDuty.get(j));
                    fakeDuty.get(j).setPeople(fakePeople.get(i));
                }
            }
        }
    }  // svyzuvanei vseh komponentov nada sovet poskolko silnay zavisemost ot elementod

    public static void clinDb() throws IOException {
        try (Database db = DatabaseBuilder.open(new File("db.mdb"))){
            String[] arr = new String[]{"user","duty","vacation"};
            for (int i = 0; i <arr.length ; i++) {
                Table table = db.getTable(arr[i]);
                for ( Row row : table) {
                    table.deleteRow(row);
                    table.reset();
                }
            }
        }catch ( Exception e){
            e.printStackTrace();
            System.out.println("======================oshobka pri udalenii");
        }
    }

    public static void wraitDb() throws IOException {
        try  (Database db = DatabaseBuilder.open(new File("db.mdb"))) {
            Table table = db.getTable("user");
            People people;
            String str;
            for (int j = 0; j <fakePeople.size() ; j++) {
                people = fakePeople.get(j);
                str = new Gson().toJson(people.getMassenger().getMasageHistory());
                table.addRow(people.getPassword(), people.getLogin(), people.getName(), people.getSoname(), people.getFname(), people.getRang(), people.getId(), people.getMassenger().isOutcoming(), people.getMassenger().isOutcoming(), str);
            }

            table = db.getTable("duty");
            Duty duty;
            for (int i = 0; i <fakeDuty.size() ; i++) {
                duty = fakeDuty.get(i);
                table.addRow(duty.getId(), duty.getData(), duty.isCertified());
            }

            table = db.getTable("vacation");
            Vacation vacation;
            for (int i = 0; i <fakeVacation.size() ; i++) {
                vacation = fakeVacation.get(i);
                table.addRow(vacation.getFirstData(), vacation.getId(), vacation.getLastData());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void removePerson(People peopleRemove) throws IOException {
        try(Database db = DatabaseBuilder.open(new File("db.mdb"))) {
            Table table = db.getTable("user");
            Cursor cursor = CursorBuilder.createCursor(table);
            if(cursor.findFirstRow(Collections.singletonMap("id",peopleRemove.getId()))){
               table.deleteRow(cursor.getCurrentRow());
            }
        }
    }
}
