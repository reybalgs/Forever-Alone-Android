## Hey! This project has been scrapped in favor of a completely new and clean project. You can find it here: <https://github.com/warmonger256/Falone-Android-ICS>. A version of FAlone-Android supporting Froyo and above is also planned.  ##

### Your forks are still welcome if you feel like diving into messy code. ###

Forever-alone-android is the Android client for Forever Alone, a college course and free-time matching app originally begun by lugkhast (https://github.com/lugkhast/Forever-Alone).

This project contains the entire UI client of the app for the Android mobile platform.

A little background history of this project:
The web app and server side of Forever Alone was originally started by lugkhast as a hobby project. Some time later, we were involved in a school project that involves creating a mobile application (not necessarily on Android). We've decided that integrating his project to a mobile application is a great idea, and now he's working on the server side while I work on the Android client.

This project is currently:
- Inoperable
- Does not actually manipulate useful data yet
- Just a broken GUI

This project reaches v.1.0 when:
- The GUI is complete
- Useful data is now being managed by the client and sent/received from the server
- It's on the Android Market.

Primary Features planned:
+ Tabbed UI layout. Separate tabs for Self schedules, Friends schedules and Settings.
+ Connected to the server that's hosted on the Google App engine, which means you only need a Google account to authenticate with the server. Since this client is on Android, authentication is effortless, painless and convenient.

You (self) tab
- Schedules are arranged according to day of the week
- Only the course code/title is displayed in the list.
- When a course code/title is tapped, a popup window will display all supporting information regarding that course (Complete name, description, schedules, rooms, sections).
- Courses that have mutual properties with friends are marked with a special color.
- The Context Menu has options to Add, Edit and Delete courses.

Friends tab
- Names of friends are displayed and arranged alphabetically by default.
- Tapping on a friend's name moves the user to a page displaying information about that friend (name, contact, schedules)
- The Context Menu has options to Add, Remove and Sort Friends According to Name/Number of Mutual Schedules(Descending).

Settings tab
- Color schemes
- Some privacy settings
- Version information
- There will also be some other settings here but since both the server and client side of Forever Alone isn't done, I can't give all planned settings and preferences.
