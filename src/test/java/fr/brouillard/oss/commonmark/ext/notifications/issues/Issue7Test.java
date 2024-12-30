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
package fr.brouillard.oss.commonmark.ext.notifications.issues;

import fr.brouillard.oss.commonmark.ext.notifications.NotificationsExtension;
import org.commonmark.Extension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Issue7Test {
    private static final Set<Extension> EXTENSIONS = Collections.singleton(NotificationsExtension.create());
    private static final Parser PARSER = Parser.builder().extensions(EXTENSIONS).build();
    private static final HtmlRenderer RENDERER = HtmlRenderer.builder().extensions(EXTENSIONS).build();

    @Test
    public void can_parse_issue_attached_file() throws IOException {
        String fileContent = new String(Files.readAllBytes(Paths.get("src", "test", "resources", "issues", "issue-7-ordered-and-unordered-lists.md")), StandardCharsets.UTF_8);
        assertThat(fileContent, startsWith("## Unordered"));
        assertThat(fileContent, endsWith("that"));

        String rendered = RENDERER.render(PARSER.parse(fileContent));
        assertThat(rendered, notNullValue());
        assertThat(rendered, startsWith("<h2>Unordered</h2>"));
    }
}
