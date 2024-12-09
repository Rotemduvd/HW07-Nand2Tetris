package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class CodeWriter {
    private final FileWriter output;
    private final String fileName;

    public CodeWriter(String filePath) throws IOException {
        String outputFilePath = filePath.replace(".vm", ".asm");
        output = new FileWriter(outputFilePath);
        fileName = Paths.get(filePath).getFileName().toString().replaceAll("\\.vm$", "");
    }

    private void writeArithmetic(String command) throws IOException {

    }

    private void writePushPop(String command, String segment, Integer index) throws IOException {
        boolean b = segment.equals("local") || segment.equals("argument") || segment.equals("this") || segment.equals("that");

        if (command.equals("C_PUSH")) {
            output.write("// push " + segment + " " + index + "\n");

            if (segment.equals("constant")) {
                output.write("@" + index + "\n"); // Load constant
                output.write("D=A\n");           // D = constant
                commonPush();                   // Perform common push operation
            } else if (b) {
                output.write("@" + shortenSeg(segment) + "\n"); // Load base address
                output.write("D=M\n");                          // D = base address
                output.write("@" + index + "\n");               // Load index
                output.write("A=D+A\n");                        // A = base + index
                output.write("D=M\n");                          // D = RAM[base + index]
                commonPush();                                   // Perform common push operation
            } else if (segment.equals("temp")) {
                output.write("@" + (5 + index) + "\n");         // Temp segment starts at R5
                output.write("D=M\n");                          // D = RAM[temp + index]
                commonPush();                                   // Perform common push operation
            } else if (segment.equals("static")) {
                output.write("@" + fileName + "." + index + "\n"); // Static variable
                output.write("D=M\n");                            // D = RAM[static variable]
                commonPush();                                     // Perform common push operation
            } else if (segment.equals("pointer")) {
                if (index == 0) {
                    output.write("@THIS\n"); // Load THIS
                } else if (index == 1) {
                    output.write("@THAT\n"); // Load THAT
                }
                output.write("D=M\n");        // D = RAM[THIS/THAT]
                commonPush();                // Perform common push operation
            }

        } else if (command.equals("C_POP")) {
            if (b) {
                output.write("// pop " + segment + " " + index + "\n");
                output.write("@" + shortenSeg(segment) + "\n");
                output.write("D=M\n");                               // D = base address
                output.write("@" + index + "\n");                    // Load index
                output.write("D=D+A\n");                             // D = base + index
                commonPop();                                         // Perform common pop operation
            } else if (segment.equals("temp")) {
                output.write("// pop temp " + index + "\n");
                output.write("@" + (5 + index) + "\n");              // Temp segment starts at R5
                output.write("D=A\n");                               // D = address of temp + index
                commonPop();                                         // Perform common pop operation
            } else if (segment.equals("static")) {
                output.write("// pop static " + index + "\n");
                output.write("@SP\n");                               // Load stack pointer
                output.write("AM=M-1\n");                            // SP--, A = RAM[SP]
                output.write("D=M\n");                               // D = RAM[SP]
                output.write("@" + fileName + "." + index + "\n");   // Static variable
                output.write("M=D\n");                               // RAM[static variable] = D
            } else if (segment.equals("pointer")) {
                output.write("// pop pointer " + index + "\n");
                output.write("@SP\n");                               // Load stack pointer
                output.write("AM=M-1\n");                            // SP--, A = RAM[SP]
                output.write("D=M\n");                               // D = RAM[SP]
                if (index == 0) {
                    output.write("@THIS\n");
                } else if (index == 1) {
                    output.write("@THAT\n");
                }
                output.write("M=D\n");                               // RAM[THIS/THAT] = D
            }
        }
    }

    private void commonPush() throws IOException {
        output.write("@SP\n");           // Load stack pointer
        output.write("A=M\n");           // A = RAM[SP]
        output.write("M=D\n");           // RAM[SP] = D
        output.write("@SP\n");           // Increment SP
        output.write("M=M+1\n");
    }

    private void commonPop() throws IOException {
        output.write("@R13\n");          // Temporary register
        output.write("M=D\n");           // R13 = target address
        output.write("@SP\n");           // Load stack pointer
        output.write("AM=M-1\n");        // SP--, A = RAM[SP]
        output.write("D=M\n");           // D = RAM[SP]
        output.write("@R13\n");          // Load target address
        output.write("A=M\n");           // A = RAM[target]
        output.write("M=D\n");           // RAM[target] = D
    }




    private String shortenSeg(String segment){
            return switch (segment) {
                case "local" -> "LCL";
                case "argument" -> "ARG";
                case "this" -> "THIS";
                case "that" -> "THAT";
                default -> throw new IllegalArgumentException("Invalid segment: " + segment);
            };
    }
}

