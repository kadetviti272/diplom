package Controllers.User;

import Models.People;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
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

    private People people;

    @FXML
    private void initialize() throws IOException {

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
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
