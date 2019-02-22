package Models;
import com.healthmarketscience.jackcess.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FakeRepositori {

    public static SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
    public static ObservableList<Duty> fakeDuty = FXCollections.observableArrayList();
    public static ObservableList<Vacation> fakeVacation = FXCollections.observableArrayList();
    public static ObservableList<People> fakePeople = FXCollections.observableArrayList();
    public static Object[] arrControler;

    static {

        try {
            ReadDB();
            clinDb();
            wraitDb();
        } catch (IOException e) {
            e.printStackTrace();
        }

        binding();
        fakeDuty.addListener(new ListChangeListener<Duty>() {
            @Override
            public void onChanged(Change<? extends Duty> c) {
                System.out.println("izmenenie v narydah");
            }
        });   // dobavlenie slushatelly dly narydov
        fakeVacation.addListener(new ListChangeListener<Vacation>() {
            @Override
            public void onChanged(Change<? extends Vacation> c) {
                System.out.println("izmenenie v otpuskax");
            }
        }); // dobavlenie slushatelly dly otpuskov

        fakePeople.addListener(new ListChangeListener<People>() {
            @Override
            public void onChanged(Change<? extends People> c) {
                System.out.println("izmenenie v cheloveakh");
            }
        }); //dbavlenie zavisimoste dly
        for (People p:fakePeople) {
            System.out.println(p);
        }
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
                        case  "text":
                            tempMasanger.setText((String) row.get(columnName));
                          //  System.out.println(tempMasanger.getText());
                            break;
                        case  "inmasage":
                            tempMasanger.setIncoming(((Boolean) row.get(columnName)).booleanValue());
                            break;
                        case  "outmasage":
                            tempMasanger.setOutcoming(((Boolean) row.get(columnName)).booleanValue());
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
                }
            }
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    public static void wraitDb() throws IOException {
        try  (Database db = DatabaseBuilder.open(new File("db.mdb"))) {

            Table table = db.getTable("user");
            People people;
            for (int j = 0; j <fakePeople.size() ; j++) {
                people = fakePeople.get(j);
                table.addRow(people.getPassword(), people.getLogin(), people.getName(), people.getSoname(), people.getFname(), people.getRang(), people.getId(), people.getMassenger().isOutcoming(), people.getMassenger().isOutcoming(), people.getMassenger().getText());
            }

            table = db.getTable("duty");
            Duty duty;
            for (int i = 0; i <fakeDuty.size() ; i++) {
                duty = fakeDuty.get(i);
                System.out.println(duty);
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

}