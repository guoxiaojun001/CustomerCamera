# CustomerCamer
觉得系统的camera不好看，实现自己的camera


1. requestWindowFeature(Window.FEATURE_NO_TITLE)无效

原因是Studio中MainActivity默认继承的是AppCompatActivity 
显然, 将AppCompatActivity改成Activity就可以了. 但不推荐这么做.

<style name="AppTheme"parent="Theme.AppCompat.Light.DarkActionBar">
   <!-- Customize your theme here. -->
   <item name="colorPrimary">@color/colorPrimary</item>
   <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
   <item name="colorAccent">@color/colorAccent</item>
   <item name="android:windowNoTitle">true</item>
</style>


在默认的style文件中AppCompat加上
<item name="android:windowNoTitle">true</item>


直接在activity标签里面加上含NoActionBar的主题也可以.

<activity android:name=".MainActivity"
       android:theme="@style/Theme.AppCompat.Light.NoActionBar">


还有一种方法是在onCreat里面执行下面语句
 if (getSupportActionBar()!=null) {
          getSupportActionBar().hide();
  }



http://blog.csdn.net/maimiho/article/details/52006168
