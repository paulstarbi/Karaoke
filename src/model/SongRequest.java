package model;

public class SongRequest {
    private String SingerName;
    private Song Song;

    public SongRequest(String singerName, model.Song song) {
        this.SingerName = singerName;
        this.Song = song;
    }

    public String getSingerName() {
        return SingerName;
    }

    public void setSingerName(String singerName) {
        SingerName = singerName;
    }

    public model.Song getSong() {
        return Song;
    }

    public void setSong(model.Song song) {
        Song = song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongRequest that = (SongRequest) o;

        if (!SingerName.equals(that.SingerName)) return false;
        return Song.equals(that.Song);
    }

    @Override
    public int hashCode() {
        int result = SingerName.hashCode();
        result = 31 * result + Song.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SongRequest{" +
                "SingerName='" + SingerName + '\'' +
                ", Song=" + Song +
                '}';
    }
}
