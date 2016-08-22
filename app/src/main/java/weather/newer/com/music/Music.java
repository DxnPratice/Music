package weather.newer.com.music;

import java.io.Serializable;

/**
 * Created by windows on 2016/8/22.
 */
public class Music  implements Serializable {
    long id;
    String name;
    String  data;
    String alumn;
    String artist;

    public Music(long id, String name, String data, String alumn, String artist) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.alumn = alumn;
        this.artist = artist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAlumn() {
        return alumn;
    }

    public void setAlumn(String alumn) {
        this.alumn = alumn;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", alumn='" + alumn + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
