package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Adminpanel {



    @FXML
    BorderPane borderPanel;

    @FXML
    private void initialize() throws IOException {
        borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/admin/AdminGeneratordDuty.fxml")));

    }

    public void checkRaport(ActionEvent actionEvent) {

    }

    public void clicPersonMeny(ActionEvent actionEvent) throws IOException {
        borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/admin/adminInfoListPerson.fxml")));
    }

    public void clicGeneratorMeny(ActionEvent actionEvent) throws IOException {
        borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/admin/AdminGeneratordDuty.fxml")));

    }

    public void cliclMesegMeny(ActionEvent actionEvent) throws IOException {
        borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/admin/adminMasengger.fxml")));
    }

}
