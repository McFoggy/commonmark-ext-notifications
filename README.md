# commonmark-ext-notifications [![Build Status](https://travis-ci.org/McFoggy/commonmark-ext-notifications.svg)](https://travis-ci.org/McFoggy/commonmark-ext-notifications)

This is a plugin for [commonmark-java](https://github.com/atlassian/commonmark-java) that allows to create _notifications_ paragraphs in markdown like the following (taken from [Isabel Castillo blog](http://isabelcastillo.com/error-info-messages-css))

![image](https://cloud.githubusercontent.com/assets/1119660/14935335/09ada1b0-0ece-11e6-9387-738a4a475923.png)

using a very simple syntax

```
! This is an info message.
!v This is a success message.
!! Consider this a warning.
!x This is an error message.
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