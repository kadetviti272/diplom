package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    public void Masanger(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }
}
