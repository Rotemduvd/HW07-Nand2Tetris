package org.example;

import java.io.IOException;

public class VMTranslator {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please add the vm filepath");
            System.exit(1);
        }

        String inputFilePath = args[0];

        try {
            // Initialize Parser and CodeWriter
            Parser parser = new Parser(inputFilePath);
            CodeWriter codeWriter = new CodeWriter(inputFilePath);

            // Process each command in the input file
            while (parser.hasMoreLines()) {

                parser.advance(); // Move to the next command
                String commandType = parser.commandType();

                switch (commandType) {
                    case "C_PUSH":
                    case "C_POP":
                        String segment = parser.arg1();
                        int index = parser.arg2();
                        codeWriter.writePushPop(commandType, segment, index);
                        break;
                    case "C_ARITHMETIC":
                        String arithmeticCommand = parser.arg1();
                        codeWriter.writeArithmetic(arithmeticCommand);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown command type: " + commandType);
                }
            }

            // Close the CodeWriter
            codeWriter.close();

        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}