Markdown is complicated, so I'm just going to document a bunch of cases that
probably don't work and that I don't feel like fixing.

## Things that should be links

[Reference], and the [same link][reference]

[reference]: https://arcticicestudio.github.io/styleguide-markdown/rules/links.html#definition-placement

<a href="https://en.wikipedia.org/wiki/HTML">Markdown allows HTML</a>, and <A
Href= https://en.wikipedia.org/wiki/Quirks_mode ignored>parsing HTML is scary

[[You can have brackets inside the link]](https://a.co/)

You don't need to specify the text to show for the HTML. Normally in Markdown,
you can surround the URL with angle brackets to force them into clickable links
rather than plain text:
<https://www.markdownguide.org/basic-syntax/#urls-and-email-addresses>. However,
most sites automatically do this to whatever looks like a URL:
https://en.wikipedia.org/wiki/Disambiguation_(disambiguation) (the parentheses are included!).

## Things that shouldn't be links

If t.co appears in the list of URLs, then it has failed the test.

You can \[escape](https<span>://</span>t.co/) a Markdown link with a backslash.

<p>
  [Normal Markdown links](https://t.co/) don't work in HTML.
</p>

```md
Nor do they work in [code blocks](https://t.co/).
```

Or `[preformated text](https://t.co/)`.

    Also, code blocks might not be fenced by backticks. [A 4-space indent works too.](https://t.co/)

## Ambiguous cases

Are relative URLs URLs?

- [Relative path](image.md)

- [Absolute path](/)

- [Query](?hi=wow)

- [Anchor](#ambiguous-cases)

- [Relative based on protocol](//www.example.com/)

Are [emails](seanthesheep22+markdownparse@outlook.com) URLs?

Must a URL start with HTTP/HTTPS? Or are [other protocols](ms-calculator://)
allowed too?
