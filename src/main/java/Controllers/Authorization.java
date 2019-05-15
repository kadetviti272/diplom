package Controllers;
import Controllers.User.UserMain;
import Models.Duty;
import Models.FakeRepositori;
import Models.People;
import Models.Vacation;
import com.healthmarketscience.jackcess.*;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import static Models.FakeRepositori.*;

public class Authorization {

    @FXML
    TextField login;
    @FXML
    TextField pssword;
    @FXML
    AnchorPane root;

    @FXML
    private void initialize(){

    }

    public void pressbutton(ActionEvent actionEvent) throws IOException {
        authorization(login.getText(),pssword.getText());
        if(isautoriz)         // rabotaet nada budet unconet when start work
        ((Stage)((Button)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void authorization(String login, String password) throws IOException {
        String[]arr = new String[]{"cheff","user"};
        Database db = DatabaseBuilder.open(new File("db.mdb"));
        String temppass="";
        String templog="";
        int huis=0;
        boolean avtorizovan = false;
        int idautorizuser =-1;
        for (int i = 0; i <arr.length ; i++) {
            Table table = db.getTable(arr[i]);
            for(Row row : table) {
                if(avtorizovan)break;
                for(Column column : table.getColumns()){
                    String columnName = column.getName();
                    Object value = row.get(columnName);
                    if(columnName.equals("password")){
                        temppass = value.toString();
                    }
                    if( columnName.equals("login")){
                        templog=value.toString();
                    }
                    if( columnName.equals("id") && i!=0){
                        idautorizuser = (Integer)value;
                    }
                    if(i==0 && columnName.equals("admin")){
                        if(value.equals(true))huis = 1;
                        else huis = 2;
                        idautorizuser=-1;
                    }else if (i==1){
                        huis = 3;
                            for (int j = 0; j < fakePeople.size() ; j++) {
                                if (fakePeople.get(j).getId() == idautorizuser) {
                                    autorizadPeopl = fakePeople.get(j);
                                    System.out.println(idautorizuser+"   ssssssssssssss");
                                }
                            }
                    }
                }
                if(templog.equals(login) && temppass.equals(password)){
                    System.out.println("zashol  "+huis);
                    avtorizovan = true;
                    System.out.println(idautorizuser);
                    checkUser(huis);
                }
            }
        }
        db.close();
    }


    boolean isautoriz = false;
    private void checkUser( int huis) throws IOException {
        //        1-admin;
        //        2-nachal;
        //        3-user;
        isautoriz=true;
        String url = null;
        switch (huis){
            case 1:
                System.out.println("zashol admin");
                url= "/Wievs/admin/adminPanel.fxml";
                showWindow(url,"Адміністратор");
                break;
            case 2:
                System.out.println("nachalnik");
                url= "/Wievs/cheff/glavaMain.fxml";
                showWindow(url,"Начальник");
                break;
            case 3:
                System.out.println("user++++");
                url= "/Wievs/user/userMain.fxml";
                showWindow(url,new People(),"Користувач");
                break;
            default:
                url=null;
                isautoriz=false;
                System.out.println("ne vernoi password");
        }
    }


    private void showWindow(String URL, String namePanel) throws IOException {
        final Stage primaryStage = new Stage();
        primaryStage.setTitle(namePanel);
        primaryStage.setOnCloseRequest(event -> {
            try {
                FakeRepositori.clinDb();
                FakeRepositori.wraitDb();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("da zakrl");

            Platform.exit();
        });
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(URL));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root, 800 , 500));
        primaryStage.show();
    }

    private void showWindow (String URL,People people, String namePanel) throws IOException {
        Stage primaryStage = new Stage();
        primaryStage.setTitle(namePanel);
        primaryStage.setOnCloseRequest(event -> { // nada bude porabotat
            try {
                FakeRepositori.clinDb();
                FakeRepositori.wraitDb();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("da zakrl");

            Platform.exit();
        });

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(URL));
        UserMain userMain = new UserMain();
        userMain.setPeople(people);
        fxmlLoader.setController(userMain);
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root,800,500));
        primaryStage.show();
    }

}
