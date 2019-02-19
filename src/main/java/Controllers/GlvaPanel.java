package Controllers;

import Models.Mans;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class GlvaPanel {

    @FXML
    ComboBox<Mans> combobox;

    public void clikChek(ActionEvent actionEvent) {
        combobox.setItems(FXCollections.observableArrayList(Mans.January, Mans.February, Mans.March, Mans.April, Mans.May, Mans.June, Mans.July, Mans.August, Mans.September, Mans.October, Mans.November, Mans.December ));

        combobox.setOnAction(event -> {
            System.out.println(combobox.getValue());
        });
    }
}
