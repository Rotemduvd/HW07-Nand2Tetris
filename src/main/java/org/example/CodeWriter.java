package org.example;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {
    private FileWriter output;

    public CodeWriter(String filePath) throws IOException {
        String outputFilePath = filePath.replace(".vm", ".asm");
        output = new FileWriter(outputFilePath);
    }

    private void writeArithmetic(String command) throws IOException {
        if
    }

    private void writePushPop(String command, String segment, Integer index) throws IOException {
        String newLine = "";
        if (command.equals("C_PUSH")) {
            if (segment.equals("constant")) {
                output.write("//push constant" + index + "\n");
                output.write("@" + index + "\n");
                output.write("D=A\n");
                output.write("// RAM[SP] =D\n");
                output.write("@SP\n");
                output.write("A=M\n");
                output.write("M=D\n");
                output.write("// SP++\n");
                output.write("@SP\n");
                output.write("M=M+1\n");
            } else if (segment.equals("local") || segment.equals("argument") || segment.equals("this") || segment.equals("that")) {
                // Push from a segment (local/argument/this/that)
                output.write("// push " + segment + " " + index + "\n");
                output.write("@" + shortenSeg(segment) + "\n");
                output.write("D=M\n");                             // D = base address
                output.write("@" + index + "\n");                  // Load index
                output.write("A=D+A\n");                           // A = base address + index
                output.write("D=M\n");                             // D = RAM[base + index]
                output.write("@SP\n");                             // Load stack pointer
                output.write("A=M\n");                             // A = RAM[SP]
                output.write("M=D\n");                             // RAM[SP] = D
                output.write("@SP\n");                             // Increment SP
                output.write("M=M+1\n");

            }
        }
    }

    private String shortenSeg (String segment){
            return switch (segment) {
                case "local" -> "LCL";
                case "argument" -> "ARG";
                case "this" -> "THIS";
                case "that" -> "THAT";
                default -> throw new IllegalArgumentException("Invalid segment: " + segment);
            };
    }
}

