package Controllers.admin;

import Controllers.User.UserMain;
import Models.FakeRepositori;
import Models.People;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class RedactPerson {

    @FXML
    TextField tName;
    @FXML
    ComboBox<String> tRang;
    @FXML
    ComboBox<String> tPos;
    @FXML
    TextField tBistDay;
    @FXML
    TextField tCall;
    @FXML
    ImageView photo;
    @FXML
    TextField indexDuty;
    @FXML
    TextField fDate;
    @FXML
    TextField lDate;

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

    // Солдат Ст.солдат Мол.сержант Сержант Ст.сержант Старшина Прапорщик Ст.прапорщик Мол.лейтенант Лейтенант Ст.лейтенант Капітан Майор Підполковник Полковник
    @FXML
    private void initialize() {

        tRang.setItems(FXCollections.observableArrayList("Солдат", "Ст.солдат", "Мол.сержант", "Сержант", "Ст.сержант", "Старшина", "Прапорщик", "Ст.прапорщик", "Мол.лейтенант", "Лейтенант", "Ст.лейтенант", "Капітан", "Майор", "Підполковник", "Полковник"));
        tPos.setItems(FXCollections.observableArrayList("Викладач", "Управлыння"));
        System.out.println("init");
        if (people == null) {
            people = new People();
        } else {
            tBistDay.setText("24/09/1999");
            tName.setText(people.getSoname() + " " + people.getSoname() + " " + people.getFname());
            tCall.setText("+380987211728");
        }
    }

    @FXML
    public void newPng(ActionEvent actionEvent) {

    }

    @FXML
    public void addDuty(ActionEvent actionEvent) {

    }

    @FXML
    public void removeDuty(ActionEvent actionEvent) {

    }

    public void finish(ActionEvent actionEvent) {
        System.out.println("nada proverka i iniwealiz novogo libo starogo elementa");
        stage.close();
    }

    public void cancel(ActionEvent actionEvent) {
        System.out.println("ne dodan ne izmeneno");
        stage.close();
    }
}
