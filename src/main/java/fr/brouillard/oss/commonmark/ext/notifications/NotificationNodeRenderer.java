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

import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;

import java.util.Collections;
import java.util.Set;

public class NotificationNodeRenderer implements NodeRenderer {
	private final HtmlNodeRendererContext context;
	private final HtmlWriter htmlWriter;
	private NotificationsExtension.DomElementMapper domElementMapper;
	private NotificationsExtension.ClassMapper classMapper;

	public NotificationNodeRenderer(HtmlNodeRendererContext context, NotificationsExtension.DomElementMapper domElementMapper, NotificationsExtension.ClassMapper classMapper) {
		this.context = context;
		this.htmlWriter = context.getWriter();
		this.domElementMapper = domElementMapper;
		this.classMapper = classMapper;
	}

	@Override
	public Set<Class<? extends Node>> getNodeTypes() {
		return Collections.singleton(NotificationBlock.class);
	}

	@Override
	public void render(Node node) {
		NotificationBlock nb = (NotificationBlock) node;

		htmlWriter.line();
		Notification n = nb.getType();
		String domElement = domElementMapper.domElement(n);
		htmlWriter.tag(domElement, Collections.singletonMap("class", classMapper.cssClass(n)));
		renderChildren(nb);
		htmlWriter.tag("/" + domElement);
		htmlWriter.line();
	}

	private void renderChildren(Node parent) {
		Node node = parent.getFirstChild();
		while (node != null) {
			Node next = node.getNext();
			context.render(node);
			node = next;
		}
	}
}
