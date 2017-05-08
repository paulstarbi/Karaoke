package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;

public class KaraokeMachine {

	private SongBook songBook;
	private BufferedReader reader;
	private Queue<Song> songQueue;
	private Map<String, String> menu;

	public KaraokeMachine(SongBook songBook) {
		this.songBook = songBook;
		this.reader = new BufferedReader(new InputStreamReader(System.in));
		this.songQueue = new ArrayDeque<Song>();
		this.menu = new HashMap<String, String>();
		this.menu.put("add", "Add a new song to the SongBook");
		this.menu.put("play", "Play next song from queue");
		this.menu.put("choose", "Choose song to sing!");
		this.menu.put("quit", "Give up. Exit the program");
	}

	private String promptAction() throws IOException {
		System.out.printf("there are %d songs avaiable and %d in the queue. Your options are: %n",
				songBook.getSongCount(), songQueue.size());
		for (Map.Entry<String, String> option : menu.entrySet()) {
			System.out.printf("%s - %s %n", option.getKey(), option.getValue());
		}
		System.out.print("What do You want to do: ");
		String choice = reader.readLine();
		return choice.trim().toLowerCase();
	}

	// how menu have to work
	public void run() {
		String choice = "";
		do {
			try {
				choice = promptAction();
				switch (choice) {
				case "add":
					Song song = promptNewSong();
					songBook.addSong(song);
					System.out.printf("%s added! %n%n", song);
					break;
				case "choose":
					String artists = promptArtist();
					Song artisSong = promptSongForArtist(artists);
					songQueue.add(artisSong);
					System.out.printf("You chose: %s%n", artisSong);
					break;
				case "play":
					playNext();
					break;
				case "quit":
					System.out.println("Thanks for play");
					break;
				default:
					System.out.printf("Unknown choice: '%s'. Try again. %n%n%n", choice);
				}
			} catch (IOException ioe) {
				System.out.println("Problem with input");
				ioe.printStackTrace();
			}
		} while (!choice.equals("quit"));
	}

	// code to add a new song
	private Song promptNewSong() throws IOException {
		System.out.println("Enter the artist's name: ");
		String artist = reader.readLine();
		System.out.println("Enter the title: ");
		String title = reader.readLine();
		System.out.println("Enter the video URL: ");
		String videoUrl = reader.readLine();

		return new Song(artist, title, videoUrl);
	}

	private String promptArtist() throws IOException {
		System.out.println("Avaiable artists:");
		List<String> artists = new ArrayList<>(songBook.getArtists());
		int index = promptForIndex(artists);
		return artists.get(index);
	}

	private Song promptSongForArtist(String artist) throws IOException {
		List<Song> songs = songBook.getSongsForArtists(artist);
		List<String> songTitles = new ArrayList<>();
		for (Song song : songs) {
			songTitles.add(song.getmTitle());
		}
		System.out.println("Avaiable songs for: " + artist);
		int index = promptForIndex(songTitles);
		return songs.get(index);
	}

	private int promptForIndex(List<String> options) throws IOException {
		int counter = 1;
		for (String option : options) {
			System.out.printf("%d.)  %s%n", counter, option);
			counter++;
		}
		System.out.println("Your choice: ");
		String optionAsString = reader.readLine();
		int choice = Integer.parseInt(optionAsString.trim());

		return choice - 1;
	}

	public void playNext() {
		Song song = songQueue.poll();
		if (song == null) {
			System.out.println("Sorry no songs in the queue.");
		} else {
			System.out.printf("%n%n%n Open %s to hear %s by %s %n%n%n", song.getmVideoURL(), song.getmTitle(),
					song.getmArtisit());
		}

	}
}
