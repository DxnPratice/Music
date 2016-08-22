package weather.newer.com.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {
    private static final String TAG ="MusicActivity " ;
    Intent intent;
    MusicAdapter  adapter;
    musicReceiver  receiver;
    ArrayList<Music> list;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
         intent=new Intent(this,MusicService.class);
        listview= (ListView) findViewById(R.id.listView);

        startService(intent);




    }

    @Override
    protected void onResume() {
        receiver=new musicReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.newer.weather.music");
        //注册广播
        super.onResume();
        //注册本地广播
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
    @Override
    protected void onStop() {
        super.onStop();
        //当活动停止的时候停止服务
        stopService(intent);
    }
    public class musicReceiver extends BroadcastReceiver{
        /**
         *
         * 当接收到广播的时候如何处理
         * @param context
         * @param intent2
         */
        @Override
        public void onReceive(Context context, Intent intent2) {
       list = (ArrayList<Music>) intent2.getSerializableExtra(MusicService.EXTRA_MUSIC);
            adapter=new MusicAdapter(MusicActivity.this,list);
            listview.setAdapter(adapter);

        }
    }
}
