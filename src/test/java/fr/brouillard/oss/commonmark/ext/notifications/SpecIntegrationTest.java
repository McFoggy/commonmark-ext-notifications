package fr.brouillard.oss.commonmark.ext.notifications;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.commonmark.Extension;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.testutil.SpecTestCase;
import org.commonmark.testutil.spec.SpecExample;
import org.junit.Test;

/**
 * Tests that the spec examples still render the same with all extensions enabled.
 */
public class SpecIntegrationTest extends SpecTestCase {

    private static final List<Extension> EXTENSIONS = Arrays.asList(NotificationsExtension.create());
    private static final Parser PARSER = Parser
    			.builder()
    			.extensions(EXTENSIONS)
    			.build();
    // The spec says URL-escaping is optional, but the examples assume that it's enabled.
    private static final HtmlRenderer RENDERER = HtmlRenderer.builder().extensions(EXTENSIONS).percentEncodeUrls(true).build();
    private static final Map<String, String> OVERRIDDEN_EXAMPLES = getOverriddenExamples();

    public SpecIntegrationTest(SpecExample example) {
        super(example);
    }

    @Test
    @Override
    public void testHtmlRendering() {
        String expectedHtml = OVERRIDDEN_EXAMPLES.get(example.getSource());
        if (expectedHtml != null) {
            assertRendering(example.getSource(), expectedHtml);
        } else {
            super.testHtmlRendering();
        }
    }

    @Override
    protected String render(String source) {
        return RENDERER.render(PARSER.parse(source));
    }

    private static Map<String, String> getOverriddenExamples() {
        Map<String, String> m = new HashMap<>();

        return m;
    }

}
