package Controllers.User;

import Models.People;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
public class UserMain {

    @FXML
    BorderPane borderPanel;
    @FXML
    MenuItem grafduty;
    @FXML
    MenuItem personcard;
    @FXML
    MenuItem usermasenger;
    @FXML
    MenuItem exit;
    @FXML
    MenuItem file;

    private People people;

    FileChooser fileChooser;

    @FXML
    private void initialize() throws IOException {

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Raports","*.docx")
        );

        borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/user/userDutyList.fxml")));

        grafduty.setOnAction(event -> {
            try {
                borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/user/userDutyList.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );

        usermasenger.setOnAction(event -> {
            try {
                borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/user/userMassenger.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        personcard.setOnAction( event -> {
            try {
              borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/user/userInfo.fxml")));
            } catch ( IOException e){
                e.printStackTrace();
            }
        });

        exit.setOnAction( event -> {
//            Stage stage = (Stage) exit.getScene().getWindow();
//            // do what you have to do
//            stage.close();
        });

        file.setOnAction(event -> {
            System.out.println("nada otkrut failovu sistemu");
            fileChooser.setInitialDirectory(new File("Рапорта"));
            try {
                Desktop.getDesktop().open( fileChooser.showOpenDialog(new Stage()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
