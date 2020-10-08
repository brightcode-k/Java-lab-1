package javapack;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.util.stream.Collectors.toList;


public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name (example - files/file.csv): ");
        String fileName = scanner.next();
        String separator = Separator();
        Filereader fileReader = new Filereader();
        List<String> lines = fileReader.read(fileName).collect(toList());
        List<String> parsedLines = new ArrayList<>();
        lines.forEach(line -> parsedLines.add(Parser.LineParser(line, separator)));
        File file = new File("result.txt");
        String absolutePath = file.getAbsolutePath();
        Files.write(Paths.get(absolutePath), parsedLines);
        System.out.println("Path to the file:");
        System.out.println(absolutePath);
    }
    public static String Separator() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter separator: ");
        String separator = in.next();
        return String.valueOf(separator);
    }
}

