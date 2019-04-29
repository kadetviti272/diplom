import Models.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.fest.util.Maps;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

import java.io.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main2 {

    public static void main(String[] args) throws IOException, ParseException {
//        System.out.println("Ss");
//        Mans mans = Mans.April;
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy--HH:mm:ss "+ Mans.April.toString());
//        System.out.println(dateFormat.format(calendar.getTime()));
//        System.out.println(  mans.toString()  + new SimpleDateFormat(" dd/MM/yyyy -- HH:mm:ss").format(Calendar.getInstance().getTime())+".docx");
        String fg = "dfdfdfdfdf";
        System.out.println(new Gson().toJson(new Massenger()));
//        ArrayList<String>f = new ArrayList<>();
//        f.add(new String("aa"));
//        f.add(new String("ff"));
//        String b = f.get(0);
//        b=null;
//        System.out.println(f);
//
//
//        Type itemsMapType = new TypeToken<List<Masage>>() {}.getType();
//        List<Masage> ss= new ArrayList<>();
//        System.out.println(new Gson().toJson(ss));
//        System.out.println("===========");
//        Massenger rt = new Massenger();
//        System.out.println(new Gson().toJson(rt));
//        System.out.println("343434343");

//        Hashtable<Boolean, String> ss = new Hashtable<>();
//        ss.put(false,"Sss");
//        ss.put(false,"sss");
//        ss.put(true,"false");
//        for (Map.Entry<Boolean,String> g :ss.entrySet()) {
//            System.out.println(g.getValue() + "  " + g.getKey());
//        }
//

//        HashMap<Boolean, String> a = new HashMap<>();
//        ArrayList<Masage> sob = new ArrayList<>();
//        sob.add(new Masage( "sss" , false));
//        sob.add(new Masage( "sss" , false));
//        sob.add(new Masage( "sss" , false));
//        sob.add(new Masage( "sss" , false));
//        sob.add(new Masage( "sss" , false));
//        System.out.println(new Gson().toJson(sob));
//        System.out.println(new Gson().toJson(sob));
//        System.out.println(new Gson().toJson(sob));
//
//
//        System.out.println(new Gson().toJson(a));
//        a.put(false,"Sss");
//        a.put(false,"sss");
//        a.put(true,"false");
//        for (Map.Entry<Boolean,String> bn :a.entrySet()) {
//            System.out.println(bn.getValue() + "  " + bn.getKey());
//        }


//
//
//        ArrayList<People> people = new ArrayList<>();
//        people.add(new People());
//
//        ArrayList<Duty> duties = new ArrayList<>();
//        duties.add(new Duty());
//        people.get(0).getListDuti().add(duties.get(0));


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
