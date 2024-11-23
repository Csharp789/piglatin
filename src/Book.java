import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList; 

public class Book
{
    // What should a book contain?
    // Ideas: need to store text, need to store current reading position
    //        title, author?, source URL, ... 
    private String title;
    private ArrayList<String> text = new ArrayList<String>();

    Book()
    {
        // Empty book
    }

    public void printlines(int start, int length)
    {
        System.out.println("Lines " + start + " to " + (start + length) + " of book: " + title);
        for (int i=start; i<start+length; i++)
        {
            if (i < text.size())
            {
                System.out.println(i + ": " + text.get(i));
            }
            else
            {
                System.out.println(i + ": line not in book.");     
            }
        }
    }

    String getTitle()
    {
        return title;
    }
    void setTitle(String title)
    {
        this.title = title;
    }

    String getLine(int lineNumber)
    {
        return text.get(lineNumber);
    }

    int getLineCount()
    {
        return text.size();
    }

    void appendLine(String line)
    {
        text.add(line);
    }

    public void readFromString(String title, String string)
    {
        // load a book from an input string.
        this.title = title;
        Scanner scanner = new Scanner(string);
        while (scanner.hasNext()) 
        {
            String line = scanner.nextLine();
            text.add(line);
        }
        scanner.close();
    }

    public void readFromUrl(String title, String url)
    {
        // load a book from a URL.
        // https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
        this.title = title;

        try {
            URL bookUrl = new URL(url);
            Scanner scanner = new Scanner(bookUrl.openStream());
            while (scanner.hasNext()) 
            {
                text.add(scanner.nextLine());
            }
            scanner.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    void writeToFile()
{
    if (text.isEmpty()) {
        System.out.println("No content to write to file.");
        return;
    }

    if (title == null || title.trim().isEmpty()) {
        System.out.println("Title is missing. Cannot create file.");
        return;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(title + ".txt", true))) {
        System.out.println("Writing to file: " + title + ".txt");
        for (int i = 0; i < text.size(); i++) {
            writer.write(text.get(i));
            writer.newLine();
        }
        System.out.println("File written successfully.");
    } catch (IOException ex) {
        System.out.println("Error writing to file: " + ex.getMessage());
        ex.printStackTrace();
    }
}
    public void writeToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < getLineCount(); i++) {
                writer.write(getLine(i)); // Write each line to the file
                writer.newLine();        // Add a new line
            }
            System.out.println("Book content written to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    
}