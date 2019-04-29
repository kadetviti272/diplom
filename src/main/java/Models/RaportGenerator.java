package Models;

import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.core.VariablePattern;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variables;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RaportGenerator {

    public static void raportChange(Duty duty, People newPeopl, People oldPeopl){
        Docx docx = new Docx("src/main/resources/shablon/s2.docx");
        docx.setVariablePattern(new VariablePattern("#{", "}"));
        Variables variables = new Variables();
        variables.addTextVariable(new TextVariable("#{oldPeople}", oldPeopl.getRang()+" "+oldPeopl.getSoname()));
        variables.addTextVariable(new TextVariable("#{newPeople}", newPeopl.getRang()+" "+newPeopl.getSoname()));
        variables.addTextVariable(new TextVariable("#{date}", new SimpleDateFormat("dd/MM/yyyy").format(duty.getDate())));
        docx.fillTemplate(variables);
        String a =  "Рапорта/Заміни/"+oldPeopl.getRang()+" "+oldPeopl.getSoname()+"-"+newPeopl.getRang()+" "+newPeopl.getSoname()+new SimpleDateFormat("dd-MM-yyyy").format(duty.getDate().getTime())+".docx";
        docx.save(a);
    }

    public static void raportMans( Mans mans){
        System.out.println("da y rabota");
        ArrayList listDuty = GeneratorDuty.getListDutiMans(mans);
        Docx docx = new Docx("src/main/resources/shablon/s1.docx");
        docx.setVariablePattern(new VariablePattern("#{", "}"));
        Variables variables = new Variables();
        String text="";
        for (int i = 0; i <32; i++) {
            try {
                variables.addTextVariable(new TextVariable("#{d"+Integer.toString(i)+"}", ((Duty)listDuty.get(i)).getPeople().getName()+"  "+i));
            }catch (Exception e){
                variables.addTextVariable(new TextVariable("#{d"+Integer.toString(i)+"}", ""));
            }
        }
        docx.fillTemplate(variables);
        String a =  "Рапорта/Графік чергування/"+  mans.toString() +
                new SimpleDateFormat(" dd-MM-yyyy (HH-mm-ss)").format(Calendar.getInstance().getTime())+".docx";
        docx.save(a);
    }


}
