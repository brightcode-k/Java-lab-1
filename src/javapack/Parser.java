package javapack;



public class Parser {

   private static final char Default_delimeter = ',';

    public static String LineParser(String cvsText, String separator2) {
        int number_symb = 0;
        boolean first_boolean = false;
        boolean second_boolean = false;
        StringBuilder complete_line = new StringBuilder();
        while(!cvsText.isEmpty()) {
            if(cvsText.startsWith(String.valueOf(Default_delimeter)) && !first_boolean && !second_boolean) {
                complete_line.append(number_symb).append(separator2);
                number_symb = 0;
            }
            else if(cvsText.startsWith("\"") && number_symb == 0) {
                int q = cvsText.indexOf("\"", 1);
                if(q != -1) {first_boolean = true;}
                else {number_symb++;}
            }
            else if(cvsText.startsWith("\"") && number_symb != 0) {
                if(first_boolean) {
                    first_boolean = false;
                }
            else if(cvsText.indexOf('/')==0 && cvsText.indexOf('*')==1) {
                second_boolean = true;
            }
            else if(cvsText.indexOf('*')==0 && cvsText.indexOf('/')==1) {
                second_boolean = false;
                cvsText = cvsText.substring(1);
            }
            else {
                number_symb++;
                }
            }
            else if (!second_boolean){
                number_symb++;
            }
            cvsText = cvsText.substring(1);
        }
        if(number_symb !=0) {
            complete_line.append(number_symb);
        }
        return complete_line.toString();
    }
}