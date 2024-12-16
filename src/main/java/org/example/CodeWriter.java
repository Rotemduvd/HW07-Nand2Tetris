package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class CodeWriter {
    private final FileWriter output;
    private String fileName;
    private int labelCount = 0;
    private int funcLabelCount = 0;
    private int callLabelCount = 0;


    public CodeWriter(String filePath) throws IOException {
        String outputFilePath = filePath.replace(".vm", ".asm");
        output = new FileWriter(outputFilePath);
        fileName = Paths.get(filePath).getFileName().toString().replaceAll("\\.vm$", "");
    }

    public void writeArithmetic(String command) throws IOException {
        output.write("// " + command + "\n");

        if (command.equals("add")) {
            output.write("@SP\n");
            output.write("A=M\n");
            output.write("A=A-1\n");
            output.write("A=A-1\n");
            output.write("D=M\n");
            output.write("A=A+1\n");
            output.write("D=D+M\n");
            output.write("@SP\n");
            output.write("M=M-1\n");
            output.write("M=M-1\n");
            output.write("A=M\n");
            output.write("M=D\n");
            output.write("@SP\n");
            output.write("M=M+1\n");
        } else if (command.equals("sub")) {
            output.write("@SP\n");
            output.write("A=M\n");
            output.write("A=A-1\n");
            output.write("A=A-1\n");
            output.write("D=M\n");
            output.write("A=A+1\n");
            output.write("D=D-M\n");
            output.write("@SP\n");
            output.write("M=M-1\n");
            output.write("M=M-1\n");
            output.write("A=M\n");
            output.write("M=D\n");
            output.write("@SP\n");
            output.write("M=M+1\n");
        } else if (command.equals("neg")) {
            output.write("@SP\n");
            output.write("A=M-1\n");
            output.write("M=-M\n");
        } else if (command.equals("eq") || command.equals("gt") || command.equals("lt")) {
            String jumpType = switch (command) {
                case "eq" -> "JEQ";
                case "gt" -> "JGT";
                case "lt" -> "JLT";
                default -> throw new IllegalArgumentException("Invalid command: " + command);
            };

            String trueLabel = "TRUE_" + labelCount;
            String endLabel = "END_" + labelCount;
            labelCount++;

            output.write("@SP\n");
            output.write("M=M-1\n");
            output.write("A=M\n");
            output.write("D=M\n");
            output.write("@SP\n");
            output.write("M=M-1\n");
            output.write("A=M\n");
            output.write("D=M-D\n");
            output.write("@" + trueLabel + "\n");
            output.write("D;" + jumpType + "\n");
            output.write("@SP\n");
            output.write("A=M\n");
            output.write("M=0\n");
            output.write("@" + endLabel + "\n");
            output.write("0;JMP\n");
            output.write("(" + trueLabel + ")\n");
            output.write("@SP\n");
            output.write("A=M\n");
            output.write("M=-1\n");
            output.write("(" + endLabel + ")\n");
            output.write("@SP\n");
            output.write("M=M+1\n");
        } else if (command.equals("and") || command.equals("or")) {
            output.write("@SP\n");
            output.write("M=M-1\n");
            output.write("A=M\n");
            output.write("D=M\n");
            output.write("@SP\n");
            output.write("M=M-1\n");
            output.write("A=M\n");

            String op = command.equals("and") ? "&" : "|";
            output.write("M=D" + op + "M\n");

            output.write("@SP\n");
            output.write("M=M+1\n");
        } else if (command.equals("not")) {
            output.write("@SP\n");
            output.write("A=M-1\n");
            output.write("M=!M\n");
        }
    }

    public void writePushPop(String command, String segment, int index) throws IOException {
        output.write("// " + command + " " + segment + " " + index + "\n");

        if (command.equals("C_PUSH")) {
            if (segment.equals("constant")) {
                // Push constant value
                output.write("@" + index + "\n");
                output.write("D=A\n");
            } else if (segment.equals("static")) {
                // Push static variable
                output.write("@" + fileName + "." + index + "\n");
                output.write("D=M\n");
            } else if (segment.equals("pointer")) {
                // Push pointer (THIS/THAT)
                String target = (index == 0) ? "THIS" : "THAT";
                output.write("@" + target + "\n");
                output.write("D=M\n");
            } else if (segment.equals("temp")) {
                // Push temp (R5-R12)
                output.write("@" + (5 + index) + "\n");
                output.write("D=M\n");
            } else if (segment.equals("argument")) {
                output.write("@" + index + "\n");
                output.write("D=A\n");
                output.write("@ARG\n");
                output.write("A=M\n");  // Dereference ARG
                output.write("D=D+A\n");  // Add offset
                output.write("A=D\n");  // Final address
                output.write("D=M\n");  // Get value at address
                commonPush();
            } else {
                // Push local, argument, this, or that
                output.write("@" + index + "\n");
                output.write("D=A\n");
                output.write("@" + shortenSeg(segment) + "\n");
                output.write("A=D+M\n");
                output.write("D=M\n");
            }
            commonPush();

        } else if (command.equals("C_POP")) {
            if (segment.equals("static")) {
                // Pop to static variable
                output.write("@SP\n");
                output.write("M=M-1\n");
                output.write("A=M\n");
                output.write("D=M\n");
                output.write("@" + fileName + "." + index + "\n");
                output.write("M=D\n");
            } else if (segment.equals("pointer")) {
                // Pop to pointer (THIS/THAT)
                String target = (index == 0) ? "THIS" : "THAT";
                output.write("@SP\n");
                output.write("M=M-1\n");
                output.write("A=M\n");
                output.write("D=M\n");
                output.write("@" + target + "\n");
                output.write("M=D\n");
            } else if (segment.equals("temp")) {
                // Pop to temp (R5-R12)
                output.write("@SP\n");
                output.write("M=M-1\n");
                output.write("A=M\n");
                output.write("D=M\n");
                output.write("@" + (5 + index) + "\n");
                output.write("M=D\n");
            } else if (segment.equals("that")) {
                // Pop to `that` with pointer updates
                output.write("@" + index + "\n");
                output.write("D=A\n");
                output.write("@THAT\n");
                output.write("A=M\n");
                output.write("D=D+A\n");
                output.write("@THAT\n");
                output.write("M=D\n"); // Update `THAT` pointer
                output.write("@SP\n");
                output.write("M=M-1\n");
                output.write("A=M\n");
                output.write("D=M\n");
                output.write("@THAT\n");
                output.write("A=M\n");
                output.write("M=D\n");
                output.write("@" + index + "\n");
                output.write("D=A\n");
                output.write("@THAT\n");
                output.write("A=M\n");
                output.write("D=A-D\n");
                output.write("@THAT\n");
                output.write("M=D\n"); // Restore `THAT`
            } else if (segment.equals("argument")) {
                output.write("@" + index + "\n");
                output.write("D=A\n");
                output.write("@ARG\n");
                output.write("A=M\n");  // Dereference ARG
                output.write("D=D+A\n");  // Add offset
                output.write("@R13\n");
                output.write("M=D\n");  // Store address in R13
                output.write("@SP\n");
                output.write("M=M-1\n");  // Decrement stack pointer
                output.write("A=M\n");
                output.write("D=M\n");  // Get value from stack
                output.write("@R13\n");
                output.write("A=M\n");
                output.write("M=D\n");  // Store value at ARG + index
            } else {
                // Pop to local, argument, this, or that
                output.write("@" + index + "\n");
                output.write("D=A\n");
                output.write("@" + shortenSeg(segment) + "\n");
                output.write("D=D+M\n");
                output.write("@R13\n");
                output.write("M=D\n");
                output.write("@SP\n");
                output.write("M=M-1\n");
                output.write("A=M\n");
                output.write("D=M\n");
                output.write("@R13\n");
                output.write("A=M\n");
                output.write("M=D\n");
            }
        }
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

    void writeLabel(String label) throws IOException {
        output.write("("+ label + ")\n");
    }

    void writeGoto(String label) throws IOException {
        output.write("@" + label + "\n");
        output.write("0;JMP\n");
    }

    void writeIf(String label) throws IOException {
        output.write("@SP\n");
        output.write("M=M-1\n");
        output.write("A=M\n");
        output.write("D=M\n");
        output.write("@" + label + "\n");
        output.write("D;JNE\n");
    }

    public void writeFunction(String function, Integer nArgs) throws IOException {
        //function entry point
        output.write("(" + function + ")\n");

        //save nArgs in order to create nArg push 0's
        output.write("@" + nArgs + "\n");
        output.write("D=A\n");
        output.write("@R13\n");
        output.write("M=D\n");

        String loopLabel = "PUSH_ZERO" + funcLabelCount;
        String endLabel = "END_PUSH" + funcLabelCount;
        funcLabelCount++;

        output.write("(" + loopLabel + ")\n");
        output.write("@R13\n");
        output.write("D=M\n");
        output.write("@" + endLabel + "\n");
        output.write("D;JEQ\n");

        // Push 0 onto the stack
        output.write("@0\n");
        output.write("D=A\n");
        output.write("@SP\n");
        output.write("A=M\n");
        output.write("M=D\n");
        output.write("@SP\n");
        output.write("M=M+1\n");

        // Decrement the counter
        output.write("@R13\n");
        output.write("M=M-1\n");

        // Jump back to the loop
        output.write("@" + loopLabel + "\n");
        output.write("0;JMP\n");
        output.write("(" + endLabel + ")\n");
    }

    void writeCall(String function, Integer nArgs) throws IOException {
        String returnAddress = "RETURN_" + callLabelCount++;

        // push return address
        output.write("// push return address\n");
        output.write("@" + returnAddress + "\n");
        output.write("D=A\n");
        commonPush();

        // Push LCL
        writePushPop("C_PUSH", "local", 0);

        // Push ARG
        writePushPop("C_PUSH", "argument", 0);

        // Push THIS
        writePushPop("C_PUSH", "this", 0);

        // Push THAT
        writePushPop("C_PUSH", "that", 0);

        // Reposition ARG
        output.write("// ARG = SP - 5 - nArgs\n");
        output.write("@SP\n");
        output.write("D=M\n");
        output.write("@5\n");
        output.write("D=D-A\n");
        output.write("@" + nArgs + "\n");
        output.write("D=D-A\n");
        output.write("@ARG\n");
        output.write("M=D\n");

        // Reposition LCL
        output.write("// LCL = SP\n");
        output.write("@SP\n");
        output.write("D=M\n");
        output.write("@LCL\n");
        output.write("M=D\n");

        // Goto function
        output.write("// goto " + function + "\n");
        output.write("@" + function + "\n");
        output.write("0;JMP\n");

        // Declare return address label
        output.write("(" + returnAddress + ")\n");
    }

    void writeReturn() throws IOException {
        // endFrame = LCL
        output.write("@LCL\n");
        output.write("D=M\n");
        output.write("@R13\n");
        output.write("M=D\n");

        // retAddr = *(FRAME - 5)
        output.write("@5\n");
        output.write("A=D-A\n");
        output.write("D=M\n");
        output.write("@R14\n");
        output.write("M=D\n");

        // *ARG = pop()
        writePushPop("C_POP", "argument", 0);

        // SP = ARG + 1
        output.write("@ARG\n");
        output.write("D=M+1\n");
        output.write("@SP\n");
        output.write("M=D\n");

        // Restore THAT
        output.write("@R13\n");
        output.write("AM=M-1\n");
        output.write("D=M\n");
        output.write("@THAT\n");
        output.write("M=D\n");

        // Restore THIS
        output.write("@R13\n");
        output.write("AM=M-1\n");
        output.write("D=M\n");
        output.write("@THIS\n");
        output.write("M=D\n");

        // Restore ARG
        output.write("@R13\n");
        output.write("AM=M-1\n");
        output.write("D=M\n");
        output.write("@ARG\n");
        output.write("M=D\n");

        // Restore LCL
        output.write("@R13\n");
        output.write("AM=M-1\n");
        output.write("D=M\n");
        output.write("@LCL\n");
        output.write("M=D\n");

        // Goto retAddr
        output.write("@R14\n");
        output.write("A=M\n");
        output.write("0;JMP\n");
    }


    private void commonPush() throws IOException {
        output.write("@SP\n");
        output.write("A=M\n");
        output.write("M=D\n");
        output.write("@SP\n");
        output.write("M=M+1\n");
    }

    private void commonPop() throws IOException {
        output.write("D=D+A\n");
        output.write("@R13\n");
        output.write("M=D\n");
        output.write("@SP\n");
        output.write("M=M-1\n");
        output.write("A=M\n");
        output.write("D=M\n");
        output.write("@R13\n");
        output.write("A=M\n");
        output.write("M=D\n");
    }

    public void close() throws IOException {
        if (output != null) {
            output.close();
        }
    }

    public void setFileName(String filePath) {
        fileName = Paths.get(filePath).getFileName().toString().replaceAll("\\.vm$", "");
    }

    public void writeBootstrap() throws IOException {
        output.write("// Bootstrap code\n");

        // SP = 256
        output.write("@256\n");
        output.write("D=A\n");
        output.write("@SP\n");
        output.write("M=D\n");

        // Call Sys.init
        writeCall("Sys.init", 0);
    }
}
