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
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlNodeRendererFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Objects;

public class NotificationsExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension {
	private final DomElementMapper domElementMapper;
	private final ClassMapper classMapper;

	private NotificationsExtension() {
		this(DefaultWrapperImplementations.DEFAULT_DOM_ELEMENT_MAPPER, DefaultWrapperImplementations.DEFAULT_CSS_CLASS_MAPPER);
	}

	private NotificationsExtension(DomElementMapper domElementMapper, ClassMapper classMapper) {
		this.domElementMapper = Objects.requireNonNull(domElementMapper);
		this.classMapper = Objects.requireNonNull(classMapper);
	}

	public NotificationsExtension withDomElementMapper(DomElementMapper domElementMapper) {
		return new NotificationsExtension(domElementMapper, this.classMapper);
	}

	public NotificationsExtension withClassMapper(ClassMapper classMapper) {
		return new NotificationsExtension(this.domElementMapper, classMapper);
	}

    public static NotificationsExtension create() {
        return new NotificationsExtension();
    }

	@Override
	public void extend(org.commonmark.parser.Parser.Builder parserBuilder) {
		parserBuilder.customBlockParserFactory(new NotificationBlockParser.Factory());
	}

	@Override
	public void extend(org.commonmark.renderer.html.HtmlRenderer.Builder htmlBuilder) {
		htmlBuilder.nodeRendererFactory(context -> new NotificationNodeRenderer(context, this.domElementMapper, this.classMapper));
	}

	@FunctionalInterface
	public interface DomElementMapper {
		String domElement(Notification n);
	}

	@FunctionalInterface
	public interface ClassMapper {
		String cssClass(Notification n);
	}
}
