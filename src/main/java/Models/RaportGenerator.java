package Models;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RaportGenerator {

    public static void raportMans( Mans mans){

        ArrayList listDuty = GeneratorDuty.getListDutiMans(mans);
        Docx docx = new Docx("src/main/resources/shablon/s1.docx");
        docx.setVariablePattern(new VariablePattern("#{", "}"));
// preparing variables
        Variables variables = new Variables();
//
//        variables.addTextVariable(new TextVariable("#{lastname}", "Stypka"));
        String text="";
        for (int i = 0; i <20; i++) {
            variables.addTextVariable(new TextVariable("#{d"+Integer.toString(i)+"}", ((Duty)listDuty.get(i)).getPeople().getName()));
        }
// fill template
        docx.fillTemplate(variables);
        String nameFile="";
// save filled .docx file
        String a =  "Рапорта/Графік чергування/"+  mans.toString() + new SimpleDateFormat(" dd-MM-yyyy (HH-mm-ss)").format(Calendar.getInstance().getTime())+".docx";
        docx.save(a);
    }

    public static void raportChangePerosn(People newDutu, People oldDutu){

    }

}
