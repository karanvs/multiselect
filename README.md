# multiselect
This is a sample repository for demonstrating the use of multiselect library. 
Multiselect library is a powerful library to select multiple images and videos efficiently.

## Getting Started

*  Add this to build.gradle

repositories
{ 
 jcenter() 
 maven 
{
 url “https://jitpack.io” 
}
}

*  Add dependency for library

dependencies { compile 'com.github.karanvs:mediaselectsample:v1.0' }

## Usage

*  Define a theme in styles.xml

<style name="AppTheme.NoActionBar" parent="Theme.AppCompat.Light.NoActionBar">
  <item name="colorPrimary">@color/colorPrimary</item>
  <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
  <item name="colorAccent">@color/colorAccent</item>
</style>

Use the colors of your choice, it will reflect in multiselect activity.

4)  Add multiselect activity under application tag and permission to your manifest

<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>

<activity
    android:name="com.veer.multiselect.MultiSelectActivity"
    android:theme="@style/AppTheme.NoActionBar">
  <intent-filter>
    <action android:name="com.veer.multiselect.MultiSelectActivity" />
    <category android:name="android.intent.category.DEFAULT" />
  </intent-filter>
</activity>

5)  For selecting multiple images , use

Intent intent = new Intent(context, MultiSelectActivity.class);
intent.putExtra(com.veer.multiselect.Util.Constants.LIMIT, limit);
intent.putExtra(com.veer.multiselect.Util.Constants.SELECT_TYPE, Constants. PATH_IMAGE);
startActivityForResult(intent,
    com.veer.multiselect.Util.Constants.REQUEST_CODE_MULTISELECT);

6) For selecting multiple videos , use
Intent intent = new Intent(context, MultiSelectActivity.class);
intent.putExtra(com.veer.multiselect.Util.Constants.LIMIT, limit);
intent.putExtra(com.veer.multiselect.Util.Constants.SELECT_TYPE, Constants.PATH_VIDEO);
startActivityForResult(intent,
    com.veer.multiselect.Util.Constants.REQUEST_CODE_MULTISELECT);

7)  Override onActivityResult and use as

@Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
  super.onActivityResult(requestCode, resultCode, data);
  if (requestCode == com.veer.multiselect.Util.Constants.REQUEST_CODE_MULTISELECT
      && resultCode == RESULT_OK) {
    paths = data.getStringArrayListExtra(com.veer.multiselect.Util.Constants.GET_PATHS);
      }
}
//here path contains a list of 
