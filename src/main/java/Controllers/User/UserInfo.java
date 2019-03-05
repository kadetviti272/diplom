package Controllers.User;

import Models.FakeRepositori;
import Models.People;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class UserInfo {

    @FXML
    Label lName;
    @FXML
    Label lSoname;
    @FXML
    Label lFname;
    @FXML
    Label lRang;
    @FXML
    Label lPosada;
    @FXML
    Label lPhone;
    @FXML
    Label lVacatoin;
    @FXML
    ImageView photo;

    People people;
    @FXML
    private void initialize(){
        if(FakeRepositori.autorizadPeopl!=null){
            people = FakeRepositori.autorizadPeopl;
            lFname.setText(people.getName());
            lName.setText(people.getName());
            lSoname.setText(people.getSoname());
            lRang.setText(people.getRang());
            lPhone.setText("dodel");
            lVacatoin.setText(initVacation());
        }else {
            System.out.println("net persona avtoriz");
        }

    }


    private String initVacation(){

        String text = "";
        for (int i = 0; i <FakeRepositori.autorizadPeopl.getListVakation().size() ; i++) {
            text.concat(text.concat(FakeRepositori.dateFormat.format(FakeRepositori.autorizadPeopl.getListVakation().get(i).getFirstData()))+ " - " + FakeRepositori.dateFormat.format(FakeRepositori.autorizadPeopl.getListVakation().get(i).getLastData()).concat("\n"));
        }
        return text;

    }
}
