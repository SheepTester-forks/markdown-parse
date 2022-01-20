
// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        main: while (currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if (nextOpenBracket == -1) {
                break;
            }
            boolean isImage = nextOpenBracket > 0 && markdown.charAt(nextOpenBracket - 1) == '!';
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            currentIndex = openParen + 1;
            int parenthesisLevel = 1;
            while (parenthesisLevel > 0) {
                currentIndex++;
                if (currentIndex >= markdown.length()) {
                    break main;
                }
                if (markdown.charAt(currentIndex) == '(') {
                    parenthesisLevel++;
                } else if (markdown.charAt(currentIndex) == ')') {
                    parenthesisLevel--;
                }
            }
            if (!isImage) {
                toReturn.add(markdown.substring(openParen + 1, currentIndex));
            }
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
