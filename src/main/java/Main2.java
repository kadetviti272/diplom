import Models.FakeRepositori;
import Models.GeneratorDuty;
import Models.Mans;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2 {

    public static void main(String[] args) throws IOException, ParseException {

        Docx docx = new Docx("/tro.docx");
        docx.setVariablePattern(new VariablePattern("#{","}"));
        Variables variables = new Variables();
        variables.addTextVariable(new TextVariable("#{tooo}","andrey"));
        variables.addTextVariable(new TextVariable("#{on}","Dobro pojalovat"));  //addTableVariable();
        docx.fillTemplate(variables);
        docx.save("/000.docx" );
        System.out.println("Programa rbotaet");


//        Date date = new Date();
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
