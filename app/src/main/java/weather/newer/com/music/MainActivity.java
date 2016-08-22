package weather.newer.com.music;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.MediaStore.Audio.Media;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //启动异步任务
        new ReadContentTask().execute();
    }

    //读取本地数据也是比较耗时的，不建议在主线程中操作
    //使用异步任务执行耗时操作
    class  ReadContentTask  extends AsyncTask<Void,Void,String>{
        /**
         *
         * 任务执行前，显示进度条,可以访问Ui
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         *
         * 在子线程中运行,不能访问ui
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(Void... params) {
            //音频视频,图片资源都在外部存储器中，所以必须有访问外部存储器的权限
            Uri uri= Phone.CONTENT_URI;//要访问联系人资源,即标志资源
            uri=Media.EXTERNAL_CONTENT_URI;
            Log.d(TAG, uri.toString());
          /*  String[] projection={
                   Phone._ID,
                    Phone.NUMBER,
                    Phone.DISPLAY_NAME
            };*/
            String[] projection={
                    Media._ID,
                    Media.DISPLAY_NAME,
                    Media.DATA,
                    Media.ALBUM,
                    Media.ARTIST
            };

            ContentResolver   cr=getContentResolver();
            //要访问的资源， 投影(想要的数据），查询条件，查询条件，是否进行排序
            Cursor c=cr.query(
                    uri,
                    projection,
                  /*  "_id>? AND _id <?",
                    new String[]{"2","9"},*/
                    null,
                    null,
                    null
            );
          /*  while(c.moveToNext()){
                long id=c.getLong(0);
                String number=c.getString(1);
                String name=c.getString(2);
                String msg=String.format("%d,%s,%s",id,number,name);
                Log.d(TAG,msg);
            }
*/

            while(c.moveToNext()){
                long id=c.getLong(0);
                String name=c.getString(1);
                String data=c.getString(2);
                String alumn=c.getString(3);
                String artist=c.getString(4);
                String msg=String.format("%d,%s,%s,%s,%s",id,name,data,alumn,artist);
                Log.d(TAG,msg);
            }
            c.close();
            return null;
        }

        /**
         *
         * 隐藏进度条，可以访问Ui
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }


    }

}




