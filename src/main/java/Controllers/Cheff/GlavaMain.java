package Controllers.Cheff;

import Models.FakeRepositori;
import Models.Mans;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class GlavaMain {

    @FXML
    BorderPane borderPanel;
    FileChooser fileChooser;


    @FXML
    private void initialize() throws IOException {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Raports","*.docx")
        );
        borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/cheff/glavaPanel.fxml")));
    }

    @FXML
    public void raports(ActionEvent actionEvent) {
        fileChooser.setInitialDirectory(new File("Рапорта"));
        try {
            Desktop.getDesktop().open( fileChooser.showOpenDialog(new Stage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mainWindow(ActionEvent actionEvent) {
        try {
            borderPanel.setCenter(FXMLLoader.load(getClass().getResource("/Wievs/cheff/glavaPanel.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
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


    public void Masanger(ActionEvent actionEvent) throws IOException {
        {
            Stage primaryStage = new Stage();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Месенджер");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Wievs/cheff/cheffMasanger.fxml"));
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root, 700, 400));
            primaryStage.show();
        }

    }
}
