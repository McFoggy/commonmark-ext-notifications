package fr.brouillard.oss.commonmark.ext.notifications;

import java.util.Collections;

import org.commonmark.Extension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class Demo {

	public static void main(String[] args) {
		Extension notificationExtension = NotificationsExtension.create();
		
		Parser parser = Parser
				.builder()
				.extensions(Collections.singleton(notificationExtension))
				.build();
		
		Node document = parser.parse("! Use Notifications Extension !!!");
		
		HtmlRenderer renderer = HtmlRenderer
				.builder()
				.extensions(Collections.singleton(notificationExtension))
				.build();
		renderer.render(document);
		/*
			<div class="notification_info">
			<p>Use Notifications Extension !!!</p>
			</div>
		 */
	}
}
