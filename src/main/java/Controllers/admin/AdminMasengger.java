package Controllers.admin;
import Models.FakeRepositori;
import Models.People;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


public class AdminMasengger {

    @FXML
    TableView<People> table;
    @FXML
    TableColumn<People,String> columPerson;
    @FXML
    Label label;
    @FXML
    Button sendButton;
    @FXML
    TextArea foolText;
    @FXML
    TextField lineText;

    private People mesegPeople;

    @FXML
    private void initialize(){
        table.setItems(FakeRepositori.fakePeople);
        columPerson.setCellValueFactory(t->t.getValue().rangProperty().concat(" ").concat(t.getValue().nameProperty().concat(" ").concat(t.getValue().sonameProperty())) );
    }

    @FXML
    public void changePeople(MouseEvent mouseEvent) {
        if((People)table.getSelectionModel().getSelectedItem()!=null){
            mesegPeople = (People)table.getSelectionModel().getSelectedItem();
            label.setText(mesegPeople.getName()+" "+mesegPeople.getSoname());
            foolText.setText(mesegPeople.getText());
        }
    }

    @FXML
    public void sendMasage(ActionEvent actionEvent) {
        mesegPeople.setText(mesegPeople.getText()+"\n\r"+"admin" + "\n\r"+ lineText.getText());
        foolText.setText(mesegPeople.getText());
    }
}
