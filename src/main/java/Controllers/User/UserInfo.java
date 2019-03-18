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
    Label lPosition;
    @FXML
    Label lVacatoin;
    @FXML
    ImageView photo;

    People people;
    @FXML
    private void initialize(){
        if(FakeRepositori.autorizadPeopl!=null){
            people = FakeRepositori.autorizadPeopl;
            lFname.setText(people.getFname());
            lName.setText(people.getName());
            lSoname.setText(people.getSoname());
            lRang.setText(people.getRang());
            lPhone.setText(people.getCall());
            lPosition.setText(people.getPosition());
            lVacatoin.setText( initVacation());


            System.out.println(people.getListDuti().size()+"  dutu" + people.getListVakation().size()+"  vacation");
        }else {
            System.out.println("net persona avtoriz");
        }
    }


    private String initVacation(){
        System.out.println("--------------");
        String text = "";
        for (int i = 0; i < people.getListVakation().size() ; i++) {
            text+=FakeRepositori.dateFormat.format(people.getListVakation().get(i).getFirstData())+ " - " + FakeRepositori.dateFormat.format(people.getListVakation().get(i).getFirstData())+"\n";
        }
        System.out.println(text);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

        return text;

    }
}
