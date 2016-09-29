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

import java.util.Collections;
import java.util.Set;

import org.commonmark.Extension;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.test.RenderingTestCase;
import org.junit.Test;

public class BlockQuoteTests extends RenderingTestCase {
	private static final Set<Extension> EXTENSIONS = Collections.emptySet();
	private static final Parser PARSER = Parser.builder().extensions(EXTENSIONS).build();
	private static final HtmlRenderer RENDERER = HtmlRenderer.builder().extensions(EXTENSIONS).build();

	@Override
	protected String render(String source) {
		return RENDERER.render(PARSER.parse(source));
	}

	@Test
	public void blockquoteMultipleLines() {
		assertRendering("> cite message1\n> cite message2", "");
	}

	@Test
	public void listInsideNotification() {
		assertRendering("> cite message:\n> - point 1\n> - point 2",
				"");
	}
}
