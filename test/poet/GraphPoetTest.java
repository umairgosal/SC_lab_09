package poet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

public class GraphPoetTest {

    @Test
    public void testPoemWithSimpleInput() throws Exception {
        GraphPoet poet = new GraphPoet(new File("test/poet/test-corpus.txt"));
        String input = "Seek to explore new and exciting synergies";
        String expected = "seek to explore strange new life and exciting synergies";

        String output = poet.poem(input);
        assertEquals(expected, output);
        // Ignore case during comparison
        assertTrue(expected.equalsIgnoreCase(output),
                "Expected: <" + expected + "> but was: <" + output + ">");
    }

    @Test
    public void testPoemNoBridgeWords() throws Exception {
        GraphPoet poet = new GraphPoet(new File("test/poet/test-corpus.txt"));
        String input = "This sentence has no bridge words";
        String expected = "this sentence has no bridge words";

        String output = poet.poem(input);
        assertEquals(expected, output,
                "Expected: <" + expected + "> but was: <" + output + ">");
    }

    @Test
    public void testPoemMultipleBridgeWords() throws Exception {
        GraphPoet poet = new GraphPoet(new File("test/poet/multiple-bridge-words-corpus.txt"));
        String input = "Life is beautiful";
        String expected = "life and is truly beautiful";

        String output = poet.poem(input);
        assertEquals(expected, output,
                "Expected: <" + expected + "> but was: <" + output + ">");
    }

    @Test
    public void testEmptyInput() throws Exception {
        GraphPoet poet = new GraphPoet(new File("test/poet/case-insensitive-corpus.txt"));
        String input = "";
        String expected = ""; // An empty string should produce an empty poem
        String output = poet.poem(input);

        assertEquals(expected, output, "Expected an empty output for empty input");
    }

    @Test
    public void testNoMatchesInCorpus() throws Exception {
        GraphPoet poet = new GraphPoet(new File("test/poet/case-insensitive-corpus.txt"));
        String input = "Unrelated words not in corpus";
        String expected = "unrelated words not in corpus"; // No enhancements should occur
        String output = poet.poem(input);

        assertEquals(expected, output, "Expected output to match input when no matches are found");
    }


    @Test
    public void testPoemWithPunctuation() throws Exception {
        GraphPoet poet = new GraphPoet(new File("test/poet/test-corpus.txt"));
        String input = "Seek to explore new and exciting synergies!";
        String expected = "seek to explore strange new life and exciting synergies!";

        String output = poet.poem(input);

        // Remove punctuation for comparison
        String normalizedExpected = expected.replaceAll("[!?.]", "");
        String normalizedOutput = output.replaceAll("[!?.]", "");

        assertEquals(normalizedExpected, normalizedOutput,
                "Expected: <" + expected + "> but was: <" + output + ">");
    }

    @Test
    public void testPoemEmptyInput() throws Exception {
        GraphPoet poet = new GraphPoet(new File("test/poet/test-corpus.txt"));
        String input = "";
        String expected = "";

        String output = poet.poem(input);
        assertEquals(expected, output,
                "Expected: <" + expected + "> but was: <" + output + ">");
    }

    @Test
    public void testPoemSingleWordInput() throws Exception {
        GraphPoet poet = new GraphPoet(new File("test/poet/test-corpus.txt"));
        String input = "Explore";
        String expected = "explore";

        String output = poet.poem(input);
        assertEquals(expected, output,
                "Expected: <" + expected + "> but was: <" + output + ">");
    }

    @Test
    public void testPoemWithLongCorpus() throws Exception {
        GraphPoet poet = new GraphPoet(new File("test/poet/long-corpus.txt"));
        String input = "We dream big and achieve big goals";
        String expected = "we always dream big and achieve great big goals";

        String output = poet.poem(input);
        assertEquals(expected, output,
                "Expected: <" + expected + "> but was: <" + output + ">");
    }
}
