
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
            int nextBacktick = markdown.indexOf("`", currentIndex);
            if (nextBacktick != -1 && nextBacktick < nextOpenBracket) {
                int nextNextBacktick = markdown.indexOf("`", nextBacktick + 1);
                // Ensure that there is no paragraph break in between the
                // backticks
                if (nextNextBacktick != -1
                        && !markdown.substring(nextBacktick, nextNextBacktick).contains("\n\n")) {
                    // Skip to the next backtick
                    currentIndex = nextNextBacktick + 1;
                    continue;
                }
                // Otherwise, the file does not have a matching backtick, so can
                // ignore this backtick.
            }
            if (nextOpenBracket > 0 && markdown.charAt(nextOpenBracket - 1) == '\\') {
                // Link is escaped
                currentIndex = nextOpenBracket + 1;
                continue;
            }
            boolean isImage = nextOpenBracket > 0 && markdown.charAt(nextOpenBracket - 1) == '!';
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            while (nextCloseBracket != -1 && markdown.charAt(nextCloseBracket - 1) == '\\') {
                nextCloseBracket = markdown.indexOf("]", nextCloseBracket + 1);
            }
            if (nextBacktick != -1 && nextBacktick < nextCloseBracket) {
                // The backtick at this point is between [ and ], so we have to
                // skip to the next backtick before looking for the actual
                // closing ]
                int nextNextBacktick = markdown.indexOf("`", nextBacktick + 1);
                if (nextNextBacktick != -1) {
                    nextCloseBracket = markdown.indexOf("]", nextNextBacktick);
                }
            }
            int openParen = markdown.indexOf("(", nextCloseBracket);
            if (openParen != nextCloseBracket + 1) {
                // There is something between the closing bracket and opening
                // parentheses
                currentIndex = nextOpenBracket + 1;
                continue;
            }
            currentIndex = openParen;
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
                // Remove whitespace at either end of URL (permitting at most
                // one newline)
                String url = markdown.substring(openParen + 1, currentIndex).replaceAll("\\A( *\\n)? *", "")
                        .replaceAll(" *(\\n *)?\\Z", "");
                if (url.contains(" ") || url.contains("\n")) {
                    currentIndex = nextOpenBracket + 1;
                } else {
                    toReturn.add(url);
                }
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
