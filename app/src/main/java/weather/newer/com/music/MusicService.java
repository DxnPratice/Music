package weather.newer.com.music;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;

public class MusicService extends Service {
    private static final String TAG = "MusicService ";
public  static final String EXTRA_MUSIC = "EXTRA_MUSIC";
    String msg;
    MusicThread  thread;
    ArrayList<Music> musiclist;
    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //子线程中加载音乐列表，加载完成，发广播，（活动接收数据）
        musiclist=new ArrayList<>();
         thread =new MusicThread();
         thread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
       return   null;
    }
    class  MusicThread   extends Thread{
        @Override
        public void run() {
            //要访问的资源
            Uri uri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
          //  Log.d(TAG, uri.toString());
            String[] projection={
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.ALBUM,
                    MediaStore.Audio.Media.ARTIST,
               
            };
            ContentResolver cr=getContentResolver();
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
            while(c.moveToNext()){
                long id=c.getLong(0);
                String name=c.getString(1);
                String data=c.getString(2);
                String alumn=c.getString(3);
                String artist=c.getString(4);
               msg=String.format("%d,%s,%s,%s,%s",id,name,data,alumn,artist);
              //  Log.d(TAG,msg);
                Music music=new Music(id,name,data,alumn,artist);
                musiclist.add(music);
                Log.d(TAG, musiclist.toString());
            }
            c.close();
            Intent intent =new Intent();
            intent.setAction("com.newer.weather.music");
            intent.putExtra(EXTRA_MUSIC, musiclist);
            LocalBroadcastManager.getInstance(MusicService.this).sendBroadcast(intent);
        }






        }




    }













