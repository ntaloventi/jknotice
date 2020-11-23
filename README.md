# jknotice
[![](https://jitpack.io/v/ntaloventi/jknotice.svg)](https://jitpack.io/#ntaloventi/jknotice)

[![GitHub release](https://img.shields.io/github/release/ntaloventi/jknotice.svg)](https://github.com/ntaloventi/jknotice/releases/)

[![Github all releases](https://img.shields.io/github/downloads/ntaloventi/jknotice/total.svg)](https://GitHub.com/ntaloventi/jknotice/releases/)


Notification Made Simple


>Gradle
```
dependencies {
   api 'com.github.ntaloventi:jknotice:0.1.2
}
```

>Manifest
```
<uses-permission android:name="android.permission.INTERNET" />
```

>Example
```
Context context = MainActivity.this;
Intent intent = new Intent(context, OtherActivity.class);
int id = 1;

JkNotice jkNotice = new JkNotice(context, intent, id);
jkNotice.setContent("Some Title", "Some Body", "Header");
jkNotice.showNotice();
```
