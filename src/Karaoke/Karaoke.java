package Karaoke;

import model.*;

public class Karaoke {

	public static void main(String[] args) {
		
		SongBook songBook = new SongBook();
		songBook.importFrom("songs.txt");
		songBook.exportTo("songs.txt");
		KaraokeMachine machine = new KaraokeMachine(songBook);
		machine.run();
		System.out.println("Saving book .....");
				}

				}
