package Controllers;
import Controllers.User.UserMain;
import Models.FakeRepositori;
import Models.People;
import com.healthmarketscience.jackcess.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Authorization {

    @FXML
    TextField login;
    @FXML
    TextField pssword;

    private People peopleAuthorizationControler;

    @FXML
    private void initialize(){

    }

    public void pressbutton(ActionEvent actionEvent) throws IOException {
        authorization(login.getText(),pssword.getText());
    }

    public void authorization(String login, String password) throws IOException {
        String[]arr = new String[]{"cheff","user"};
        Database db = DatabaseBuilder.open(new File("db.mdb"));

        String temppass="";
        String templog="";
        int huis=0;
        boolean avtorizovan = false;
        int idautorizuser=-1;
        for (int i = 0; i <arr.length ; i++) {
            Table table = db.getTable(arr[i]);
            for(Row row : table) {
                if(avtorizovan)break;
                for(Column column : table.getColumns()) {
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
//        1-admin;
//        2-nachal;
//        3-user;
    }


    private void checkUser( int huis) throws IOException {
        //        1-admin;
        //        2-nachal;
        //        3-user;
        String url = null;
        switch (huis){
            case 1:
                System.out.println("zashol admin");
                url= "/Wievs/admin/adminPanel.fxml";
                showWindow(url);
                break;
            case 2:
                System.out.println("nachalnik");
                url= "/Wievs/cheff/glavaPanel.fxml";
                showWindow(url);
                break;
            case 3:
                System.out.println("user++++");
                url= "/Wievs/user/userMain.fxml";
                showWindow(url,new People());
                break;
            default:
                url=null;
                System.out.println("ne vernoi password");
        }
        if(url!=null){
            String ss = "srs";
        }
    }


    private void showWindow(String URL) throws IOException {
        final Stage primaryStage = new Stage();
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("da zakrl");
        });
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(URL));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800 , 500));
        primaryStage.show();
    }

    private void showWindow (String URL,People people) throws IOException {
        Stage primaryStage = new Stage();

        primaryStage.setOnCloseRequest(event -> { // nada bude porabotat
            System.out.println("da zakrl");
        });

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(URL));
        UserMain userMain = new UserMain();
        userMain.setPeople(people);
        fxmlLoader.setController(userMain);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle(Integer.toString(people.getId()));
        primaryStage.setScene(new Scene(root,500,300));
        primaryStage.show();
    }

}
