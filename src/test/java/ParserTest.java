import org.example.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private static final String TEST_FILE = "BasicTest.vm";
    private Parser parser;

    @BeforeEach
    public void setUp() throws IOException {
        // Create the test file with the provided VM content
        File file = new File(TEST_FILE);
        if (!file.exists()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("// This file is part of www.nand2tetris.org\n" +
                        "// and the book \"The Elements of Computing Systems\"\n" +
                        "// by Nisan and Schocken, MIT Press.\n" +
                        "// File name: projects/7/MemoryAccess/BasicTest/BasicTest.vm\n\n" +
                        "// Executes pop and push commands.\n\n" +
                        "push constant 10\n" +
                        "pop local 0\n" +
                        "push constant 21\n" +
                        "push constant 22\n" +
                        "pop argument 2\n" +
                        "pop argument 1\n" +
                        "push constant 36\n" +
                        "pop this 6\n" +
                        "push constant 42\n" +
                        "push constant 45\n" +
                        "pop that 5\n" +
                        "pop that 2\n" +
                        "push constant 510\n" +
                        "pop temp 6\n" +
                        "push local 0\n" +
                        "push that 5\n" +
                        "add\n" +
                        "push argument 1\n" +
                        "sub\n" +
                        "push this 6\n" +
                        "push this 6\n" +
                        "add\n" +
                        "sub\n" +
                        "push temp 6\n" +
                        "add\n");
            }
        }

        // Initialize the parser
        parser = new Parser(TEST_FILE);
    }

    @Test
    public void testParserInitialization() {
        assertNotNull(parser);
        assertTrue(parser.hasMoreLines());
    }

    @Test
    public void testCommandProcessing() {
        while (parser.hasMoreLines()) {
            parser.advance();
            String commandType = parser.commandType();
            System.out.println("Command: " + parser.line + ", Type: " + commandType);

            if (commandType.equals("C_PUSH") || commandType.equals("C_POP")) {
                assertNotNull(parser.arg1());
                assertTrue(parser.arg2() >= 0);
            } else if (commandType.equals("C_ARITHMETIC")) {
                assertNotNull(parser.arg1());
            }
        }
    }

    @Test
    public void testCommandCounts() {
        int count = 0;
        while (parser.hasMoreLines()) {
            parser.advance();
            count++;
        }
        assertEquals(21, count); // Verify number of valid commands
    }
}