package javapack;



public class Parser {

   private static final char Default_delimeter = ',';

    public static String LineParser(String cvsText, String separator) {
        int iterator = 0;
        boolean first_boolean = false;
        boolean second_boolean = false;
        StringBuilder complete_line = new StringBuilder();
        while (!cvsText.isEmpty()) {
            if (cvsText.startsWith(String.valueOf(Default_delimeter)) && !first_boolean && !second_boolean) {
                complete_line.append(iterator).append(separator);
                iterator = 0;
            }
            else if (cvsText.startsWith("\"") && iterator == 0) {
                int delimeter_comma = cvsText.indexOf("\"", 1);
                if (delimeter_comma != -1) {
                    first_boolean = true;
                }
                else {
                    iterator++;
                }
            }
            else if (cvsText.startsWith("\"") && iterator != 0) {
                if (first_boolean) {
                    first_boolean = false;
                } else {
                    iterator++;
                }
            }
            else if (cvsText.indexOf('/') == 0 && cvsText.indexOf('*') == 1) {
                second_boolean = true;
            }
            else if (cvsText.indexOf('*') == 0 && cvsText.indexOf('/') == 1) {
                second_boolean = false;
                cvsText = cvsText.substring(1);
            }
            else if (!second_boolean) {
                iterator++;
            }
        cvsText = cvsText.substring(1);
    }
        if(iterator !=0) {
            complete_line.append(iterator);
        }
        return complete_line.toString();
    }
}