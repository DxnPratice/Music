package weather.newer.com.music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by windows on 2016/8/22.
 */
public class MusicAdapter  extends BaseAdapter{
    /**
     * 数据（依赖数据）
     */
    private ArrayList<Music> data;

    /**
     * 上下文（访问应用程序中各种资源）
     */
    private Context context;

    /**
     * 布局实例化服务
     */
    private LayoutInflater layoutInflater;

    /**
     * 构造方法
     *
     * @param context 上下文
     * @param data    数据
     */
    public MusicAdapter(Context context, ArrayList<Music> data) {
        this.data = data;
        this.context = context;

        // 获得布局实例化服务
        layoutInflater = LayoutInflater.from(context);


    }


@Override
    public int getCount() {
        return data.size();
    }

    /**
     * 获得特定位置的数据项（Car 对象）
     *
     * @param position 位置（索引）
     * @return Car position 位置的 Car 对象
     */
    @Override
    public Music getItem(int position) {
        return data.get(position);
    }

    /**
     * 获得特定数据项的 ID (数据库中 PK)
     *
     * @param position 位置
     * @return long 数据编号（PK）
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * 获得特定位置的视图项
     *
     * @param position    位置
     * @param convertView 可以复用的视图
     * @param parent      父元素（ListView）
     * @return View 特定位置的视图项
     */
    @Override
    public View getView(
            int position,
            View convertView,
            ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            // 没有可复用
            // 创建一个视图项
            convertView = layoutInflater.inflate(R.layout.music_item, parent, false);
            // 当前的 ViewHolder 保存视图的结构
            holder = new ViewHolder(convertView);
            // 视图绑定一个对象
            convertView.setTag(holder);
        } else {
            // 获得视图绑定的对象
            holder = (ViewHolder) convertView.getTag();
        }
        // 绑定数据（数据项）
        Music music = data.get(position);
        // 在视图项绑定数据项
        holder.bindData(music);

//        Log.d("getView", "holder: " + holder.id + ", position: " + position);
        return convertView;
    }


    /**
     * 视图项的结构
     */
    class ViewHolder {


        TextView  id;
        TextView name;
        TextView data;
        TextView alumn;
        TextView artist;


        /**
         * 获得视图中各个控件引用
         *
         * @param v View 视图项
         */
        public ViewHolder(View v) {


        id = (TextView) v.findViewById(R.id.textView_id);
      name = (TextView) v.findViewById(R.id.textview_name);
       data = (TextView) v.findViewById(R.id.textView_data);
      alumn= (TextView) v.findViewById(R.id.textView_alumn);
    artist = (TextView) v.findViewById(R.id.textView_artist);

        }

        /**
         * 给视图项绑定数据
         *
         * @param car Car 数据项
         */
        public void bindData(Music car) {

         id.setText(String.valueOf(car.getId()));
            name.setText(car.getName());
            data.setText(car.getData());
            alumn.setText(car.getAlumn());
            artist.setText(car.getArtist());

        }
    }
}


