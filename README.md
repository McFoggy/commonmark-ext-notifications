# commonmark-ext-notifications

[![Maven Central status](https://img.shields.io/maven-central/v/fr.brouillard.oss/commonmark-ext-notifications.svg)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A"fr.brouillard.oss"%20AND%20a%3A"commonmark-ext-notifications")
![Java CI with Maven](https://github.com/McFoggy/commonmark-ext-notifications/workflows/Java%20CI%20with%20Maven/badge.svg)

This is a plugin for [commonmark-java](https://github.com/atlassian/commonmark-java) that allows to create _notifications_ paragraphs in markdown like the following (taken from [Isabel Castillo blog](http://isabelcastillo.com/error-info-messages-css))

![image](https://cloud.githubusercontent.com/assets/1119660/14935335/09ada1b0-0ece-11e6-9387-738a4a475923.png)

using a very simple syntax

```
! This is an info message.
!v This is a success message.
!! Consider this a warning.
!x This is an error message.
```

## Usage

Simply add the extension to the `Parser` & `Renderer` objects.

```java
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
```

### Configuration

If you want specific rendering to adapt to an existing CSS framework you can define which HTML node will be rendered (see `NotificationsExtension.withDomElementMapper`) or which CSS classes (see `NotificationsExtension.withClassMapper`) will be applied.

For exemple to render [Boostrap Alerts](https://getbootstrap.com/docs/5.0/components/alerts/), you could use:

```java
Extension notificationExtension = NotificationsExtension.create()
        .withClassMapper(n -> n == Notification.ERROR ? "alert alert-danger" : "alert alert-" + n.name().toLowerCase());

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
	<div class="alert alert-info">
	<p>Use Notifications Extension !!!</p>
	</div>
 */
```


## Maven coordinates

You will find the latest version of the extension in [maven central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22fr.brouillard.oss%22%20AND%20a%3A%22commonmark-ext-notifications%22).

```
<dependency>
    <groupId>fr.brouillard.oss</groupId>
    <artifactId>commonmark-ext-notifications</artifactId>
    <version>1.0.0</version>
</dependency>    
```

## Build & release

### Normal build

- `mvn clean install`

### Release

- `mvn -Poss clean install`: this will simulate a full build for oss delivery (javadoc, source attachement, GPG signature, ...)
- `git tag -a -s -m "release X.Y.Z, additionnal reason" X.Y.Z`: tag the current HEAD with the given tag name. The tag is signed by the author of the release. Adapt with gpg key of maintainer.
    - Matthieu Brouillard command:  `git tag -a -s -u 2AB5F258 -m "release X.Y.Z, additionnal reason" X.Y.Z`
    - Matthieu Brouillard [public key](https://sks-keyservers.net/pks/lookup?op=get&search=0x8139E8632AB5F258)
- `mvn -Poss,release -DskipTests deploy`
- `git push --follow-tags origin master`