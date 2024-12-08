package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private static List<String> CmdOnly;
    private static int currLineInd = -1;
    public static String line;
    static String command;

    // Constructor: Reads the file and initializes commands
    public Parser(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        CmdOnly = new ArrayList<>();
        currLineInd = -1; // Reset current line index

        while (scanner.hasNextLine()) {
            String temp = scanner.nextLine().trim();

            if (temp.isEmpty() || temp.startsWith("//")) {
                continue;
            }

            temp = temp.replaceAll("\\s+", ""); // Remove all whitespaces
            CmdOnly.add(temp);
        }
        scanner.close();
    }

    // Reset parser to allow reprocessing
    public static void reset() {
        currLineInd = -1; // Reset the current line index
        line = null;      // Clear the current line
        command = null; // Clear the current command type
        System.out.println("Parser has been reset.");
    }

    // Checks if there are more lines to process
    public static boolean hasMoreLines() {
        return currLineInd < CmdOnly.size() - 1;
    }

    // Advances to the next line and updates the current line
    public static void advance() {
        if (hasMoreLines()) {
            currLineInd++;
            line = CmdOnly.get(currLineInd);
        } else {
            throw new IllegalStateException("No more lines to read.");
        }
    }

    public static String commandType(){
        if (line.startsWith("push")){
            command = "C_PUSH";
        } else if (line.startsWith("pop")){
            command = "C_POP";
        }else{
            command = "C_ARITHMETIC";
        }
        return command;
    }

    public static void arg1(){

    }

    public static void arg2(){

    }
}