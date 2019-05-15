package Controllers.Cheff;

import Models.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.text.ParseException;
import java.util.Calendar;

public class GlvaPanel {

    @FXML
    ComboBox<Mans> combobox;
    FileChooser fileChooser;
    @FXML
    TableView<Duty> table;
    @FXML
    TableColumn<Duty,String> col1;
    @FXML
    TableColumn<Duty,String> col2;
    @FXML
    TableColumn<Duty,Boolean> col3;
    @FXML
    CheckBox chekbox;


    @FXML
    private void initialize() throws ParseException {

        combobox.setItems(FXCollections.observableArrayList(Mans.January, Mans.February, Mans.March, Mans.April, Mans.May, Mans.June, Mans.July, Mans.August, Mans.September, Mans.October, Mans.November, Mans.December ));
        combobox.getSelectionModel().select(Calendar.getInstance().getTime().getMonth());



        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Raports","*.docx")
        );

        combobox.setOnAction(event -> {
             table.setItems(FXCollections.observableArrayList(GeneratorDuty.getListDutiMans(combobox.getValue())));
             if(table.getItems().size() > 0 && table.getItems().get(0).isCertified()){
                 chekbox.setSelected(true);

             }else if(table.getItems().size() > 0 && !table.getItems().get(0).isCertified()){
                chekbox.setSelected(false);
             }
             if(table.getItems().size()==0){
                 chekbox.setSelected(false);
             }
        });


        col1.setCellValueFactory( t -> new SimpleStringProperty( GeneratorDuty.dateFormat.format(t.getValue().getData())));
        col2.setCellValueFactory(t-> t.getValue().getPeople().nameProperty().concat(" ").concat(t.getValue().getPeople().sonameProperty()));
        col3.setCellValueFactory( t -> new SimpleBooleanProperty(t.getValue().isCertified()));
        col3.setCellFactory(t->new TableCell<Duty,Boolean>(){
            @Override
            protected void updateItem(Boolean item, boolean empty){
                super.updateItem(item,empty);
                setText(empty?null:item?"затверджено":"незатверджено");
            }
        });
        table.setItems(FXCollections.observableArrayList(GeneratorDuty.getListDutiMans(combobox.getValue())));
        if (table.getItems().get(0).isCertified())
            chekbox.setSelected(true);

     }

    public void clikChek(ActionEvent actionEvent) {
        if(chekbox.isSelected()){
            GeneratorDuty.getListDutiMans(combobox.getValue()).stream().forEach(duty -> duty.setCertified(true));
            RaportGenerator.raportMans(combobox.getValue());
        }else{
            GeneratorDuty.getListDutiMans(combobox.getValue()).stream().forEach(duty -> duty.setCertified(false));
        }
        table.refresh();
    }

}
