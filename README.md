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