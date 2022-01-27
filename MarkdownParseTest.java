import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testTestFile() throws IOException {
        String markdown = Files.readString(Path.of("test-file.md"));
        assertEquals(List.of("https://something.com", "some-page.html"), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testBasicLink() throws IOException {
        String markdown = Files.readString(Path.of("basic-link.md"));
        assertEquals(List.of("https://sheeptester.github.io/"), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testEmpty() throws IOException {
        String markdown = Files.readString(Path.of("empty.md"));
        assertEquals(List.of(), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testEscaping() throws IOException {
        String markdown = Files.readString(Path.of("escaping.md"));
        assertEquals(List.of("hi2.html"), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testImage() throws IOException {
        String markdown = Files.readString(Path.of("image.md"));
        assertEquals(List.of(), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testSquareBracketGang() throws IOException {
        String markdown = Files.readString(Path.of("square-bracket-gang.md"));
        assertEquals(List.of("before", "after"), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testUnclosedParens() throws IOException {
        String markdown = Files.readString(Path.of("unclosed-parens.md"));
        assertEquals(List.of("test", "test?test[=3", "ok"), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testWeirdUrls() throws IOException {
        String markdown = Files.readString(Path.of("weird-urls.md"));
        assertEquals(List.of("https://en.wikipedia.org/wiki/1984_(disambiguation)"), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testFile1() throws IOException {
        String contents= Files.readString(Path.of("./test-file.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    
}
