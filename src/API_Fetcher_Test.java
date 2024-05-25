import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import api_fetcher.API_Fetcher;

public class API_Fetcher_Test {

    @Test
    public void testProcessInput() {
        // Happy cases
        String input = "english";
        String actualOutput = API_Fetcher.processInput(input);
        assertTrue(actualOutput.contains("\" - "));
        
        input = "ENGLISH";
        actualOutput = API_Fetcher.processInput(input);
        assertTrue(actualOutput.contains("\" - "));
        
        input = "russian";
        actualOutput = API_Fetcher.processInput(input);
        assertTrue(actualOutput.contains("\" - "));
        
        input = "RUSSIAN";
        actualOutput = API_Fetcher.processInput(input);
        assertTrue(actualOutput.contains("\" - "));

        // Sad cases
        input = "italian";
        String expectedOutput = "The language selection entered is not currently supported, please try again.";
        actualOutput = API_Fetcher.processInput(input);
        assertEquals(expectedOutput, actualOutput);
        
        input = "french";
        actualOutput = API_Fetcher.processInput(input);
        assertEquals(expectedOutput, actualOutput);
    }
}