public class App {
    public static void main(String[] args) {
        TestSuite.run();
        translateBook("Romeo and Julliete", 
            "https://www.gutenberg.org/cache/epub/1513/pg1513.txt",
            "RomeoAndJuliet_PigLatin.txt"
        );
        translateBook(
            "Pride and Prejudice",
            "https://www.gutenberg.org/cache/epub/1342/pg1342.txt",
            "PrideAndPrejudice_PigLatin.txt"
        );
    }
    private static void translateBook(String title, String url, String outputFileName) {
        Book input = new Book();
        input.readFromUrl(title, url);
        System.out.println("Original Text (" + title + "):");
        input.printlines(0, 2);
        Book output = PigLatinTranslator.translate(input);
        System.out.println("Translated Text (" + title + "):");
        output.writeToFile(outputFileName);
        System.out.println("Translated text saved to: " + outputFileName);
        
    }
}
