package model;

import java.io.*;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.print.attribute.HashAttributeSet;

public class SongBook {

	private List<Song> Songs;

	public SongBook() {
		Songs = new ArrayList<Song>();
	}

	public void addSong(Song song) {
		Songs.add(song);
	}

	public int getSongCount() {
		return Songs.size();
	}

	// FixMe this should by fix
	private Map<String, List<Song>> byArtist() {
		Map<String, List<Song>> byArtist = new TreeMap<>();
		for (Song song : Songs) {
			List<Song> artistSongs = byArtist.get(song.getmArtisit());
			if (artistSongs == null) {
				artistSongs = new ArrayList<>();
				byArtist.put(song.getmArtisit(), artistSongs);
			}
			artistSongs.add(song);
		}
		return byArtist;
	}

	public Set<String> getArtists() {
		return byArtist().keySet();
	}

	public List<Song> getSongsForArtists(String artist) {
		// very iteresting method to sort, ovveride comparator inside method
		List<Song> songs = byArtist().get(artist);
//		songs.sort(new Comparator<Song>(){
//			@Override
//			public int compare(Song s1, Song s2){
//				if(s1.equals(s2)) return 0;
//				return s1.getmTitle().compareTo(s2.getmTitle());
//			}
//			
//		}
		return songs;
	}

	public void exportTo(String filmname) {
		try (FileOutputStream fos = new FileOutputStream(filmname); PrintWriter writer = new PrintWriter(fos);) {
			for (Song song : Songs) {
				writer.printf("%s|%s|%s%n", song.getmArtisit(), song.getmTitle(), song.getmVideoURL());
			}
		} catch (IOException ioe) {
			System.out.printf("Problem saving %s %n", filmname);
			ioe.printStackTrace();

		}
	}

	public void importFrom(String fileName){
		try(
		FileInputStream fis = new FileInputStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));){
			String line;
			while((line = reader.readLine())!= null){
				String[] args = line.split("\\|");
				addSong(new Song(args[0], args[1],args[2]));
			}}
			catch(IOException ioe){
				System.out.printf("Problem loading %s %n", fileName);
				ioe.printStackTrace();
			}
		}
	}

