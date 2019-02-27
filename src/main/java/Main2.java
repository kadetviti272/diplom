import Models.*;
import com.google.gson.Gson;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main2 {

    public static void main(String[] args) throws IOException, ParseException {
//       HashMap<String, String> a = new HashMap<>();
//        System.out.println(new Gson().toJson(a));
//        for (Map.Entry<String,String> ss :a) {
//            ss.getValue();
//            ss.getKey();
//        }
//
//
//        ArrayList<People> people = new ArrayList<>();
//        people.add(new People());
//
//        ArrayList<Duty> duties = new ArrayList<>();
//        duties.add(new Duty());
//        people.get(0).getListDuti().add(duties.get(0));
//
//        people.clear();
//        System.out.println(duties.get(0).getPeople().toString());

//        Docx docx = new Docx("/tro.docx");
//        docx.setVariablePattern(new VariablePattern("#{","}"));
//        Variables variables = new Variables();
//        variables.addTextVariable(new TextVariable("#{tooo}","andrey"));
//        variables.addTextVariable(new TextVariable("#{on}","Dobro pojalovat"));  //addTableVariable();
//        docx.fillTemplate(variables);
//        docx.save("/000.docx" );
//        System.out.println("Programa rbotaet");


//        Date dateg = new Date();
//        System.out.println(date);
//        System.out.println(date.getMonth());

//
//        ArrayList<String> arr = new ArrayList<String>();
//        arr.add("ss");
//        arr.add("dd");
//        arr.add("bb");
//        ArrayList<String>arr2=arr;
//
//        arr2.remove(1);
//        System.out.println(arr);

    }



    public static void test2() throws ParseException {SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FakeRepositori fr = new FakeRepositori();
        GeneratorDuty.GeneratorDutyMans(Mans.April);
    }

    public static void test1(){
//        People p = new People(11,"11","22","33","44","sss","ss");
//
//        for (Vacation d:FakeRepositori.fakeVacation){
//            System.out.println(d);
//        }
//
//        FakeRepositori.peopleWorkList.add(p);
//        FakeRepositori.peopleWorkList.get(1).setName("sss");
    }
}
