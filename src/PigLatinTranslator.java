import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PigLatinTranslator
{
    public static Book translate(Book input) {
        Book translatedBook = new Book();
        translatedBook.setTitle(input.getTitle());
        String outputFilePath = "PigLatinOutput.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (int i = 0; i < input.getLineCount(); i++) {
                String line = input.getLine(i);
                if (line == null || line.trim().isEmpty()) {
                    translatedBook.appendLine(line);
                    writer.write(line);
                    writer.newLine();
                } else {
                    String translatedLine = translate(line);
                    translatedBook.appendLine(translatedLine);
                    writer.write(translatedLine); 
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        return translatedBook;
    }
    public static String translate(String input) {
        StringBuilder result = new StringBuilder();
        if (input == null || input.isEmpty()) return input;
        String[] words = input.split(" ", -1);
    if(!(input.isEmpty())){
        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                result.append(translateWord(words[i]));
            }
            if (i < words.length - 1) {
                result.append(" "); // Add spaces between words
            }
        }
    }
    
        return result.toString();
    }    
    private static String translateWord(String input) {
        if (input == null || input.trim().isEmpty()) {
            return input; // Return empty or whitespace strings as-is
        }
    
        // Preserve punctuation at the end of words
        String punctuation = "";
        while (!input.isEmpty() && !Character.isLetterOrDigit(input.charAt(input.length() - 1))) {
            punctuation = input.charAt(input.length() - 1) + punctuation;
            input = input.substring(0, input.length() - 1);
        }
        if (input.isEmpty()) return punctuation; // Handle cases like "." or "..."        
    
        if (input.contains("-")) {
            String[] parts = input.split("-");
            StringBuilder translated = new StringBuilder();
            for (int i = 0; i < parts.length; i++) {
                translated.append(translateWord(parts[i]));
                if (i < parts.length - 1) {
                    translated.append("-");
                }
            }
            return translated + punctuation;
        }
    
        // Check if the word starts with a vowel
        if (input == null || input.trim().isEmpty()) {
            return input; // Return empty or whitespace strings as-is
        }        
        char firstChar = input.charAt(0);
        boolean isCapitalized = Character.isUpperCase(firstChar);
    
        if (isVowel(Character.toLowerCase(firstChar))) {
            String translated = input + "ay";
            return isCapitalized ? capitalize(translated) + punctuation : translated + punctuation;
        } else {
            // Find the first vowel in the word
            int vowelIndex = -1;
            for (int i = 0; i < input.length(); i++) {
                if (isVowel(input.charAt(i))) {
                    vowelIndex = i;
                    break;
                }
            }
    
            // If no vowel is found, return the original word with punctuation
            if (vowelIndex == -1) {
                return input + punctuation;
            }
    
            String translated = input.substring(vowelIndex) + input.substring(0, vowelIndex) + "ay";
            return isCapitalized ? capitalize(translated) + punctuation : translated + punctuation;
        }
    }
    private static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }

    private static String capitalize(String word) {
        if (word.isEmpty()) return word;
    
        char first = Character.toUpperCase(word.charAt(0));
        String rest = word.substring(1).toLowerCase(); // Convert the rest to lowercase
        return first + rest;
    }
    
    
    
    
    
    
}
