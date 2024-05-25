package api_fetcher;

import java.util.HashMap;
import java.util.Scanner;
import com.google.gson.Gson;

import util.Http_Util;
import util.Quote;

//make a main class with repl
//helper function to call the api
//maybe helper to format it as well

public class API_Fetcher {
	
	private static String url = "http://api.forismatic.com/api/1.0/";
	private static HashMap<String, String> api_args = new HashMap<>();
	
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        while (true) {
	        // Prompt the user for input
	        System.out.println("Please enter \"English\" for an English quote and \"Russian\" for a Russian quote: ");
	
	        // Read the input from the user
	        String userInput = scanner.nextLine();
	        
	        System.out.println(processInput(userInput));
	        System.out.println();
        }
    }
    
    public static String processInput(String input) {
    	// Clear args just in case they're left over from last time
        api_args.clear();
        
        // Add new args for current request
        api_args.put("method", "getQuote");
        api_args.put("format", "json");
        
        try {
        	// Attempt to create an enum value from the user input
	        Language_Options attempt = Language_Options.fromString(input.toLowerCase());
	        
	        switch (attempt) {
	        	case ENGLISH:
	        		api_args.put("lang", "en");
	        		break;
	        	case RUSSIAN:
	        		api_args.put("lang", "ru");
	        		break;
        		default:
        			// If user enters invalid language, return error
        			return "The language selection entered is not currently supported, please try again.";
	        }
	        
        } catch (IllegalArgumentException ex) {
        	// If user enters invalid language, return error
        	return "The language selection entered is not currently supported, please try again.";
        }
        
        // Make the http req and give results to user
        try {
			String res = Http_Util.sendGetRequest(url, api_args);
			
			Gson gson = new Gson();
	        Quote quote = gson.fromJson(res, Quote.class);
	        
			return "\"" + quote.getQuoteText() + "\" - " + quote.getQuoteAuthor();
		} catch (Exception e) {
			// Would be nice to pass along WHAT exactly went wrong
			// Notify user that the request didn't work
			return "Something went wrong. Please try again later.";
		}
    }
}
