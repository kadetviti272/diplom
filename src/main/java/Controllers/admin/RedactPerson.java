package Controllers.admin;

import Models.*;
import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class RedactPerson {
    @FXML
    TableView<Vacation> tabVacation ;
    @FXML
    TableColumn<Vacation,String> colVacation;
    @FXML
    TextField tSoname;
    @FXML
    DatePicker dBirsday;
    @FXML
    DatePicker fDate;
    @FXML
    DatePicker lDate;
    @FXML
    Label lVacation;
    @FXML
    TextField tName;
    @FXML
    TextField tFname;
    @FXML
    ComboBox<String> tRang;
    @FXML
    ComboBox<String> tPos;
//    @FXML
//    TextField tBistDay;
    @FXML
    TextField tCall;
    @FXML
    ImageView photo;
    @FXML
    TextField indexDuty;
    @FXML
    TextField tLogin;
    @FXML
    TextField tPassword;


    Stage stage;
    People people;

    public Stage getStage() {
        return stage;
    }

    public RedactPerson setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }


    private void idForPeople(People people){
        if(people.getId()<=0){
            int id =0;
            for (int i = 0; i < FakeRepositori.fakePeople.size(); i++) {
                if(id<FakeRepositori.fakePeople.get(i).getId())
                    id=FakeRepositori.fakePeople.get(i).getId();
            }
            people.setId(++id);
        }
    }

    StringConverter<LocalDate> converter;
    // Солдат Ст.солдат Мол.сержант Сержант Ст.сержант Старшина Прапорщик Ст.прапорщик Мол.лейтенант Лейтенант Ст.лейтенант Капітан Майор Підполковник Полковник
    @FXML
    private void initialize() {
        idForPeople(people);
        tabVacation.setItems(FXCollections.observableArrayList(people.getListVakation()));
        colVacation.setCellValueFactory(t->new SimpleStringProperty(GeneratorDuty.dateFormat.format(t.getValue().getFirstData())+" - "+GeneratorDuty.dateFormat.format(t.getValue().getLastData())));
        converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        dBirsday.setConverter(converter);
        dBirsday.setPromptText("dd/MM/yyyy");
        lDate.setConverter(converter);
        lDate.setPromptText("dd/MM/yyyy");
        fDate.setConverter(converter);
        fDate.setPromptText("dd/MM/yyyy");

        tRang.setVisibleRowCount(6);
        tRang.setItems(FXCollections.observableArrayList("Солдат", "Ст.солдат", "Мол.сержант", "Сержант", "Ст.сержант", "Старшина", "Прапорщик", "Ст.прапорщик", "Мол.лейтенант", "Лейтенант", "Ст.лейтенант", "Капітан", "Майор", "Підполковник", "Полковник"));
        tPos.setItems(FXCollections.observableArrayList("Викладач", "Управління"));
        tRang.setValue(people.getRang());
        tPos.setValue(people.getPosition());
        tLogin.setText(people.getLogin());
        tPassword.setText(people.getPassword());
        System.out.println("init");
        if (people == null) {
            people = new People();
        } else {
            if(people.getdBirsday()!=null)
            dBirsday.setValue(converter.fromString(GeneratorDuty.dateFormat.format(people.getdBirsday())));
            tName.setText(people.getName());
            tFname.setText(people.getFname());
            tSoname.setText(people.getSoname());
            tCall.setText(people.getCall());
            tRang.setVisibleRowCount(6);
        }
    }

    @FXML
    public void newPng(ActionEvent actionEvent) {

    }

    @FXML  // gavno kod zavisimostey
    public void addVacat(ActionEvent actionEvent) throws ParseException {
        System.out.println(people.getId());
        if(lDate.getValue()!=null && fDate.getValue()!=null){
            people.getListVakation().add( new Vacation(GeneratorDuty.dateFormat.parse(converter.toString(fDate.getValue())),
                    GeneratorDuty.dateFormat.parse(converter.toString(lDate.getValue())),people));
            System.out.println(people.getListVakation().size());
            System.out.println("Peopl id "+people.getId()+"  ");
            lDate.setValue(null);
            fDate.setValue(null);
            tabVacation.setItems(FXCollections.observableArrayList(people.getListVakation()));
            tabVacation.refresh();
        }
    }


    @FXML
    public void delVacat(ActionEvent actionEvent) {  // ne RABOTAET NADA PROVERYT POCHEMU
        if(tabVacation.getSelectionModel().getSelectedItem()!=null){
            people.getListVakation().removeAll(tabVacation.getSelectionModel().getSelectedItems());
            tabVacation.setItems(FXCollections.observableArrayList(people.getListVakation()));
            tabVacation.refresh();
            System.out.println(people.getListVakation().size());
        }
    }

    private void colectPeople() throws ParseException {

        FakeRepositori.fakePeople.remove(people);
        people.setSoname(tSoname.getText());
        people.setName(tName.getText());
        people.setFname(tFname.getText());
        people.setRang(tRang.getValue());
        people.setPosition(tPos.getValue());
        people.setdBirsday(GeneratorDuty.dateFormat.parse(converter.toString(dBirsday.getValue())));
        people.setCall(tCall.getText());
        people.setPassword(tPassword.getText());
        people.setLogin(tLogin.getText());
        FakeRepositori.fakePeople.add(people);
    }

    public void finish(ActionEvent actionEvent) throws ParseException {
        System.out.println("nada proverka i iniwealiz novogo libo starogo elementa");
        colectPeople();
        stage.close();
    }

    public void cancel(ActionEvent actionEvent) {
        System.out.println("ne dodan ne izmeneno");
        stage.close();;
    }
}
