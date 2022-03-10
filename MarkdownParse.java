
// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

class LinkVisitor extends AbstractVisitor {
    ArrayList<String> links = new ArrayList<>();

    @Override
    public void visit(Link link) {
        links.add(link.getDestination());
        visitChildren(link);
    }
}

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        Parser parser = Parser.builder().build();
        Node node = parser.parse(markdown);
        LinkVisitor visitor = new LinkVisitor();
        node.accept(visitor);
        return visitor.links;
    }

    public static Map<String, List<String>> getLinks(File dirOrFile) throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        if (dirOrFile.isDirectory()) {
            for (File f : dirOrFile.listFiles()) {
                result.putAll(getLinks(f));
            }
            return result;
        } else {
            Path p = dirOrFile.toPath();
            int lastDot = p.toString().lastIndexOf(".");
            if (lastDot == -1 || !p.toString().substring(lastDot).equals(".md")) {
                return result;
            }
            ArrayList<String> links = getLinks(Files.readString(p));
            result.put(dirOrFile.getPath(), links);
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        File fileOrDir = new File(args[0]);
        if (fileOrDir.isDirectory()) {
            System.out.println(getLinks(fileOrDir));
        } else {
            Path fileName = Path.of(args[0]);
            String contents = Files.readString(fileName);
            ArrayList<String> links = getLinks(contents);
            System.out.println(links);
        }
    }
}
