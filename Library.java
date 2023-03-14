import javax.swing.text.AbstractDocument;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  //private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  //podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public boolean download(AudioContent content)
	{
		// if the downloading content is a Song
		if (content.getType().equals(Song.TYPENAME))
		{
			Song tmpSong = (Song) content;
			// Loops through the songs arrayList to see if it contains the given song
			for (Song song : songs)
			{
				if (song.equals(tmpSong))
				{
					// song is found in arraylist
					errorMsg = "Song already downloaded";
					return false;
				}
			}
			songs.add(tmpSong);
			return true;
		}
		// else if the downloading content is an AudioBook
		else if (content.getType().equals(AudioBook.TYPENAME))
		{
			AudioBook tmpBook = (AudioBook) content;
			// Iterates through the audiobooks arrayList to see if it already contains the given AudioBook
			for (AudioBook book : audiobooks)
			{
				if (book.equals(tmpBook))
				{
					// audiobook is found in arrayList
					errorMsg = "Audiobook already downloaded";
					return false;
				}
			}
			audiobooks.add(tmpBook);
			return true;
		}

		return true;
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		// iterates through song arraylist
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			// prints song number
			System.out.print("" + index + ". ");
			// prints song info
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		// iterates through audiobooks arraylist
		for (int i = 0; i < audiobooks.size(); i++)
		{
			int index = i;
			// prints audiobook number
			System.out.print(index + 1 + ". ");
			// prints audiobook info
			audiobooks.get(i).printInfo();
			System.out.println();
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		// iterates through playlist arraylist
		for (int i = 0; i < playlists.size(); i++)
		{
			int index = i + 1;
			// prints playlist number
			System.out.print("" + index + ". ");
			// prints playlist title
			System.out.println(playlists.get(i).getTitle());
		}
		
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
		ArrayList<String> artists = new ArrayList<>();
		for (Song song : songs)
		{
			String currentArtist = song.getArtist();
			boolean found = false;
			// checks if the currentArtist is already in the artists arraylist
			for (String artist : artists)
			{
				if (artist.equals(currentArtist))
					found = true;
			}
			// if currentArtist is not in artists arraylist, add them into it
			if (!found)
				artists.add(currentArtist);
		}

		// iterate through the artists and print them
		for (int i = 0; i < artists.size(); i++)
		{
			System.out.println(i + 1 + ". " + artists.get(i));
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public boolean deleteSong(int index)
	{
		// checks if given index is valid
		if (index < 1 || index > songs.size())
		{
			errorMsg = "SONG NOT FOUND";
			return false;
		}

		// store the desired song to remove to a temp. variable
		Song target = songs.get(index - 1);

		// removes given song from library
		songs.remove(index - 1);

		// iterates through all playlists
		for (Playlist playlist : playlists)
		{
			// retrieves the playlist contents
			ArrayList<AudioContent> contents = playlist.getContent();
			// iterates through the contents
			for (int i = 0; i < contents.size(); i++)
			{
				// removes target song from playlist
				if (contents.get(i).equals(target))
					playlist.deleteContent(i + 1);
			}
		}

		return true;
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort()
		// passes the SongYearComparator to sort by year
		Collections.sort(songs, new SongYearComparator());
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song song1, Song song2)
		{
			// if song1 is older than song2
			if (song1.getYear() < song2.getYear())
				return -1;
			// if song1 is the same year as song2
			else if (song1.getYear() == song2.getYear())
				return 0;
			else
				return 1;
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort()
		Collections.sort(songs, new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare (Song song1, Song song2)
		{
			// if song1 is shorter than song2
			if (song1.getLength() < song2.getLength())
				return -1;
			// if song1 is the same length as song2
			else if (song1.getLength() == song2.getLength())
				return 0;
			else
				return 1;
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		Collections.sort(songs);
		// class Song should implement the Comparable interface
		// see class Song code
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public boolean playSong(int index)
	{
		// checks to see if the given song exists
		if (index < 1 || index > songs.size())
		{
			errorMsg = "Song Not Found";
			return false;
		}
		// calls play if it exists
		songs.get(index-1).play();
		return true;
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		return false;
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		return false;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public boolean playAudioBook(int index, int chapter)
	{
		// checks if the audiobook exists
		if (index < 1 || index > audiobooks.size())
		{
			errorMsg = "AudioBook Not Found";
			return false;
		}
		// checks if the given chapter exists
		if (chapter < 1 || chapter > audiobooks.get(index - 1).getChapters().size())
		{
			errorMsg = "Chapter Not Found";
			return false;
		}
		// if audiobook and chapter exist, first select the chapter
		audiobooks.get(index - 1).selectChapter(chapter);
		// then call play
		audiobooks.get(index - 1).play();
		return true;
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public boolean printAudioBookTOC(int index)
	{
		// checks if the given book exists, sets the error message if it doesn't
		if (index < 1 || index > audiobooks.size())
		{
			errorMsg = "Audiobook Not Found";
			return false;
		}
		// calls printTOC for the given audiobook
		audiobooks.get(index - 1).printTOC();
		return true;
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public boolean makePlaylist(String title)
	{
		Playlist tmpPlaylist = new Playlist(title);
		// check if playlist already exists
		for (Playlist playlist : playlists)
		{
			// sets error message if playlist already exists
			if (playlist.equals(tmpPlaylist))
			{
				errorMsg = "Playlist " + title + " Already Exists";
				return false;
			}
		}
		// adds new playlist to arraylist of playlists
		playlists.add(tmpPlaylist);
		return true;
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public boolean printPlaylist(String title)
	{
		// iterates through playlists to find the desired playlist
		for (Playlist playlist : playlists)
		{
			if (playlist.getTitle().equals(title))
			{
				//calls printContents if playlist is found
				playlist.printContents();
			}
			return true;
		}
		// sets error message if playlist not found
		errorMsg = "PLAYLIST NOT FOUND";
		return false;
	}
	
	// Play all content in a playlist
	public boolean playPlaylist(String playlistTitle)
	{
		// iterates through playlists
		for (Playlist playlist : playlists)
		{
			// checks if the given playlist exists
			if (playlist.getTitle().equals(playlistTitle))
			{
				// plays all contents in the given playlist
				playlist.playAll();
				return true;
			}
		}
		// sets error message if playlist not found
		errorMsg = "PLAYLIST NOT FOUND";
		return false;
	}
	
	// Play a specific song/audiobook in a playlist
	public boolean playPlaylist(String playlistTitle, int indexInPL)
	{
		// iterates through playlists
		for (Playlist playlist : playlists)
		{
			// checks if the given playlist exists
			if (playlist.getTitle().equals(playlistTitle))
			{
				System.out.print(playlistTitle);
				// plays a specific contents in the given playlist
				playlist.play(indexInPL);
				return true;
			}
		}
		// sets error message if playlist not found
		errorMsg = "PLAYLIST NOT FOUND";
		return false;
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public boolean addContentToPlaylist(String type, int index, String playlistTitle)
	{
		//TODO
		// what if the given playlist DNE?
		// what if the given type DNE?

		// obtains the desired content
		AudioContent newContent;
		if (type.toUpperCase().equals(Song.TYPENAME))
		{
			// check if index is valid
			if (index < 1 || index > songs.size())
			{
				errorMsg = "INVALID INDEX";
				return false;
			}
			newContent = songs.get(index - 1);
		}
		else if (type.toUpperCase().equals(AudioBook.TYPENAME))
		{
			// check if index is valid
			if (index < 1 || index > audiobooks.size())
			{
				errorMsg = "INVALID INDEX";
				return false;
			}
			newContent = audiobooks.get(index - 1);
		}
		else
		{
			errorMsg = "INVALID TYPE";
			return false;
		}

		// finds the target playlist
		for (Playlist playlist : playlists)
		{
			// if the target playlist is found, add content to playlist
			if (playlist.getTitle().equals(playlistTitle))
			{
				// check if content is already in playlist
				for (AudioContent content : playlist.getContent())
				{
					// if content already exists
					if (content.equals(newContent))
					{
						// set error message
						errorMsg = "CONTENT ALREADY EXISTS";
						return false;
					}
				}
				playlist.addContent(newContent);
				return true;
			}
		}
		// if playlist not found, set error message
		errorMsg = "PLAYLIST NOT FOUND";
		return false;
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public boolean delContentFromPlaylist(int index, String title)
	{
		// iterates through arraylist of playlists
		for (Playlist playlist : playlists)
		{
			// checks if playlist is found
			if (playlist.getTitle().equals(title))
			{
				// checks if index is valid
				if (playlist.contains(index))
				{
					// deletes audio content
					playlist.deleteContent(index);
					return true;
				}
				// sets errors message if index is not valid
				errorMsg = "INDEX OUT OF RANGE";
				return false;
			}
		}
		// sets error message if playlist not found
		errorMsg = "PLAYLIST NOT FOUND";
		return false;
	}
	
}

