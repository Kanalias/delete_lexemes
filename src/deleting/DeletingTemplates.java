package deleting;

import java.util.ArrayList;
import java.util.Arrays;

public class DeletingTemplates {

    private String text;
    private String templates;
    private ArrayList<String> arrayTemplates;

    public DeletingTemplates(String text, String templates){
        this.text = text;
        this.templates = templates;
        arrayTemplates = new ArrayList<>();
        stringToArray();
    }

    private void stringToArray(){
        arrayTemplates.addAll(Arrays.asList(templates.split("\\s")));
    }

    public String getFinalText(){
        String finalText = text;
        for(String word: arrayTemplates){
            finalText = finalText.replaceAll("(\\b" + word + "\\b)","");
        }
        return finalText;
    }

}
