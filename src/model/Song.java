package model;

public class Song {
	private String mArtisit;
	private String mTitle;
	private String mVideoURL;
	public Song(String mArtisit, String mTitle, String mVideoURL) {
		this.mArtisit = mArtisit;
		this.mTitle = mTitle;
		this.mVideoURL = mVideoURL;
	}
	
	public String getmArtisit() {
		return mArtisit;
	}
	public String getmTitle() {
		return mTitle;
	}
	public String getmVideoURL() {
		return mVideoURL;
	}

	@Override
	public String toString() {
		return String.format("Song: %s by %s", mTitle, mArtisit);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Song song = (Song) o;

		if (mArtisit != null ? !mArtisit.equals(song.mArtisit) : song.mArtisit != null) return false;
		if (mTitle != null ? !mTitle.equals(song.mTitle) : song.mTitle != null) return false;
		return mVideoURL != null ? mVideoURL.equals(song.mVideoURL) : song.mVideoURL == null;
	}

	@Override
	public int hashCode() {
		int result = mArtisit != null ? mArtisit.hashCode() : 0;
		result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
		result = 31 * result + (mVideoURL != null ? mVideoURL.hashCode() : 0);
		return result;
	}
}
