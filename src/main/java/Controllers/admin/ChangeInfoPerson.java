package Controllers.admin;

import Models.People;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChangeInfoPerson {

    private People people;
    static  int a;

    @FXML
    Label pos;

    public ChangeInfoPerson(){
        people = new People();
        a++;
    }

    @FXML
    private void initialize(){
        pos.setText(Integer.toString(a));
    }

    @FXML
    public void okButton(ActionEvent actionEvent) {
        System.out.println("++");
    }

    @FXML
    public void otmenaButton(ActionEvent actionEvent) {
        System.out.println("++");
    }

    @FXML
    public void addOtpusk(ActionEvent actionEvent) {
        System.out.println("++");
    }

    @FXML
    public void deletOtpusk(ActionEvent actionEvent) {

    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
