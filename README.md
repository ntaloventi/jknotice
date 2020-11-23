# jknotice
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
