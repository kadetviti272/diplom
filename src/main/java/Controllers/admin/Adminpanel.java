package Controllers.admin;

import Models.FakeRepositori;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Adminpanel {

    @FXML
    BorderPane borderPanel;

    @FXML
    private void initialize() throws IOException {
        borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/admin/AdminGeneratordDuty.fxml")));
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Raports","*.docx")
        );
    }

    FileChooser fileChooser;

    @FXML
    public void checkRaport(ActionEvent actionEvent) {
        fileChooser.setInitialDirectory(new File("Рапорта"));
        try {
            Desktop.getDesktop().open(fileChooser.showOpenDialog(new Stage()));
        } catch (IOException e) {
        }
    }

    @FXML
    public void clicPersonMeny(ActionEvent actionEvent) throws IOException {
        borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/admin/adminInfoListPerson.fxml")));
    }

    @FXML
    public void clicGeneratorMeny(ActionEvent actionEvent) throws IOException {
        borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/admin/AdminGeneratordDuty.fxml")));
    }

    Stage primaryStage;
    public void cliclMesegMeny(ActionEvent actionEvent) throws IOException {
        if(primaryStage ==null){
            primaryStage = new Stage();
            primaryStage.setResizable(false);
            System.out.println("rabotaet");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Wievs/admin/adminMasengger.fxml"));
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }else {
            primaryStage.hide();
            primaryStage.show();
        }
    }

    public void exit(ActionEvent actionEvent) {
        try {
            FakeRepositori.clinDb();
            FakeRepositori.wraitDb();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }
}
