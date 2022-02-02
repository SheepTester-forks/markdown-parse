// javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParseTest.java && java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest

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

    // IF YOU WANT A TEST FOR EACH OF THEIR TEST FILES
    @Test
    public void testTestFile() throws IOException {
        String markdown = Files.readString(Path.of("test-file.md"));
        assertEquals(List.of("https://something.com", "some-page.html"), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testTestFile2() throws IOException {
        String markdown = Files.readString(Path.of("test-file2.md"));
        assertEquals(List.of("https://something.com", "some-page.html"), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testTestFile3() throws IOException {
        String markdown = Files.readString(Path.of("test-file3.md"));
        assertEquals(List.of(), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testTestFile4() throws IOException {
        String markdown = Files.readString(Path.of("test-file4.md"));
        assertEquals(List.of(), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testTestFile5() throws IOException {
        String markdown = Files.readString(Path.of("test-file5.md"));
        assertEquals(List.of(), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testTestFile6() throws IOException {
        String markdown = Files.readString(Path.of("test-file6.md"));
        assertEquals(List.of(), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testTestFile7() throws IOException {
        String markdown = Files.readString(Path.of("test-file7.md"));
        assertEquals(List.of(), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testTestFile8() throws IOException {
        String markdown = Files.readString(Path.of("test-file8.md"));
        assertEquals(List.of(), MarkdownParse.getLinks(markdown));
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
    public void testImage2() throws IOException {
        String markdown = Files.readString(Path.of("image2.md"));
        assertEquals(List.of("google.com"), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testIncorrect() throws IOException {
        String markdown = Files.readString(Path.of("incorrect.md"));
        assertEquals(List.of(), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testNewFile() throws IOException {
        String markdown = Files.readString(Path.of("new-file.md"));
        assertEquals(List.of("google.com", "some-()()([][][][])()()page().html"), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testFailureInducingInput() throws IOException {
        String markdown = Files.readString(Path.of("failure-inducing-input.md"));
        assertEquals(List.of(""), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void testGitHubActions() {
        assertTrue(true);
    }
}
