package javapack;
import java.util.ArrayList;
import java.util.List;


public class Parser {

    private static final char Default_delimeter = ',';
    private static final char Default_quote = '"';

    public static List<String> LineParser(String cvsLine) {
        return LineParser(cvsLine, Default_delimeter, Default_quote);
    }
    public static List<String> LineParser(String cvsLine, char separators) {
        return LineParser(cvsLine, separators, Default_quote);
    }




    public static List<String> LineParser(String cvsText, char delimeters, char customQuote) {
        List<String> result = new ArrayList<>();
            if (cvsText == null || cvsText.isEmpty()) {
                return result;
            }
            if (customQuote == ' ') {
                customQuote = Default_quote;
            }

            if (delimeters == ' ') {
                delimeters = Default_delimeter;
            }

            StringBuffer CurVal = new StringBuffer();
            boolean inQuotes = false;
            boolean startCollectChar = false;
            boolean doubleQuotesInColumn = false;
            char[] chars = cvsText.toCharArray();

            for (char ch : chars) {
                if (inQuotes) {
                    startCollectChar = true;
                    if (ch == customQuote) {
                        inQuotes = false;
                        doubleQuotesInColumn = false;
                    } else {
                        if (ch == '\"') {
                            if (!doubleQuotesInColumn) {
                                CurVal.append(ch);
                                doubleQuotesInColumn = true;
                            }
                        } else {
                            CurVal.append(ch);
                        }
                    }
                } else {
                    if (ch == customQuote) {
                        inQuotes = true;
                        if (chars[0] != '"' && customQuote == '\"') {
                            CurVal.append('"');
                        }
                        if (startCollectChar) {
                            CurVal.append('"');
                        }
                    } else if (ch == delimeters) {
                        result.add(CurVal.toString());
                        CurVal = new StringBuffer();
                        startCollectChar = false;
                    } else if (ch == '\n') {
                        break;
                    } else {
                        CurVal.append(ch);
                    }
                }

            }
            result.add(CurVal.toString());
            return result;
        }
}

