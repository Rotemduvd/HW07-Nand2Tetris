import org.example.CodeWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class CodeWriterTest {

    @Test
    public void testStackArithmetic() throws IOException {
        String inputFilePath = "StackTest.vm"; // Replace with the actual input file path
        String outputFilePath = inputFilePath.replace(".vm", ".asm");

        // Create the VM input file
        try (FileWriter writer = new FileWriter(inputFilePath)) {
            writer.write("// This file is part of www.nand2tetris.org\n");
            writer.write("// Executes a sequence of arithmetic and logical operations\n");
            writer.write("push constant 17\n");
            writer.write("push constant 17\n");
            writer.write("eq\n");
            writer.write("push constant 17\n");
            writer.write("push constant 16\n");
            writer.write("eq\n");
            writer.write("push constant 16\n");
            writer.write("push constant 17\n");
            writer.write("eq\n");
            writer.write("push constant 892\n");
            writer.write("push constant 891\n");
            writer.write("lt\n");
            writer.write("push constant 891\n");
            writer.write("push constant 892\n");
            writer.write("lt\n");
            writer.write("push constant 891\n");
            writer.write("push constant 891\n");
            writer.write("lt\n");
            writer.write("push constant 32767\n");
            writer.write("push constant 32766\n");
            writer.write("gt\n");
            writer.write("push constant 32766\n");
            writer.write("push constant 32767\n");
            writer.write("gt\n");
            writer.write("push constant 32766\n");
            writer.write("push constant 32766\n");
            writer.write("gt\n");
            writer.write("push constant 57\n");
            writer.write("push constant 31\n");
            writer.write("push constant 53\n");
            writer.write("add\n");
            writer.write("push constant 112\n");
            writer.write("sub\n");
            writer.write("neg\n");
            writer.write("and\n");
            writer.write("push constant 82\n");
            writer.write("or\n");
            writer.write("not\n");
        }

        // Translate the VM file to ASM
        CodeWriter codeWriter = new CodeWriter(inputFilePath);
        codeWriter.writeArithmetic("add");
        codeWriter.writePushPop("C_PUSH", "constant", 17);
        codeWriter.writePushPop("C_PUSH", "constant", 16);
        codeWriter.writeArithmetic("eq");
        codeWriter.writeArithmetic("lt");
        codeWriter.writeArithmetic("gt");
        codeWriter.writeArithmetic("and");
        codeWriter.writeArithmetic("or");
        codeWriter.writeArithmetic("not");
        codeWriter.close();

        // Check if the output file exists
        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists(), "The output ASM file was not created.");

        // Optional: Compare the expected ASM output with the generated output
        // String expectedOutput = "..."; // Provide the expected ASM code
        // String generatedOutput = Files.readString(Paths.get(outputFilePath));
        // assertEquals(expectedOutput, generatedOutput);
    }
}