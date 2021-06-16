/**
 * Copyright (C) 2016 Matthieu Brouillard [http://oss.brouillard.fr/jgitver] (matthieu@brouillard.fr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.brouillard.oss.commonmark.ext.notifications;

import org.commonmark.Extension;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.testutil.RenderingTestCase;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

public class BootstrapTest extends RenderingTestCase {
    private static final Set<Extension> EXTENSIONS = Collections.singleton(
            NotificationsExtension.create()
                .withClassMapper(n -> n == Notification.ERROR ? "alert alert-danger" : "alert alert-" + n.name().toLowerCase())
    );
    private static final Parser PARSER = Parser.builder().extensions(EXTENSIONS).build();
    private static final HtmlRenderer RENDERER = HtmlRenderer.builder().extensions(EXTENSIONS).build();

    @Override
    protected String render(String source) {
        return RENDERER.render(PARSER.parse(source));
    }

    @Test
    public void infoNotification() {
        assertRendering("! info message", "<div class=\"alert alert-info\">\n<p>info message</p>\n</div>\n");
    }

    @Test
    public void errorNotification() {
        assertRendering("!x info message", "<div class=\"alert alert-danger\">\n<p>info message</p>\n</div>\n");
    }
}
