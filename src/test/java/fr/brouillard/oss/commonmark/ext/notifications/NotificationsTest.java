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

public class NotificationsTest extends RenderingTestCase {

	private static final Set<Extension> EXTENSIONS = Collections.singleton(NotificationsExtension.create());
	private static final Parser PARSER = Parser.builder().extensions(EXTENSIONS).build();
	private static final HtmlRenderer RENDERER = HtmlRenderer.builder().extensions(EXTENSIONS).build();

	@Override
	protected String render(String source) {
		return RENDERER.render(PARSER.parse(source));
	}

	@Test
	public void infoNotification() {
		assertRendering("! info message", "<div class=\"notification_info\">\n<p>info message</p>\n</div>\n");
	}

	@Test
	public void infoNotificationMultipleLines() {
		assertRendering("! info message1\n! info message2", "<div class=\"notification_info\">\n<p>info message1\ninfo message2</p>\n</div>\n");
	}

	@Test
	public void warningNotification() {
		assertRendering("!! warning message", "<div class=\"notification_warning\">\n<p>warning message</p>\n</div>\n");
	}

	@Test
	public void warningNotificationMultipleLines() {
		assertRendering("!! warning message1\n!! warning message2", "<div class=\"notification_warning\">\n<p>warning message1\nwarning message2</p>\n</div>\n");
	}

	@Test
	public void errorNotification() {
		assertRendering("!x error message", "<div class=\"notification_error\">\n<p>error message</p>\n</div>\n");
	}

	@Test
	public void errorNotificationMultipleLines() {
		assertRendering("!x error message1\n!x error message2", "<div class=\"notification_error\">\n<p>error message1\nerror message2</p>\n</div>\n");
	}

	@Test
	public void successNotification() {
		assertRendering("!v success message", "<div class=\"notification_success\">\n<p>success message</p>\n</div>\n");
	}

	@Test
	public void successNotificationMultipleLines() {
		assertRendering("!v success message1\n!v success message2", "<div class=\"notification_success\">\n<p>success message1\nsuccess message2</p>\n</div>\n");
	}
	
	@Test
	public void embeddedMessageAndListInsideNotification() {
		assertRendering("! info message:\n! - point 1\n! - point 2", "<div class=\"notification_info\">\n<p>info message:</p>\n<ul>\n<li>point 1</li>\n<li>point 2</li>\n</ul>\n</div>\n");
	}
}
