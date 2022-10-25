
package javafxapplication4.custom;

import javafx.scene.control.TextField;

// H NUMBERTEXTFIELD POU EINAI PAIDI THS TEXTFIELD STHN OPOIA 8A GINETAI TO VALIDATION 

public class NumberTextField extends TextField{
    
    //TO VALIDATION
    @Override
    public void replaceText(int i, int il, String string){
        if(string.matches("[0-9]") || string.isEmpty() ){
            super.replaceText(i, il, string);
        }
    }

    @Override
    public void replaceSelection(String string){
        super.replaceSelection(string);
    }
}