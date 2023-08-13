public class Playlist{
	//Attributes
	private String name;
	private String description;
	private Song [] songList;
	private int totalSongs; //index (i) for songList

	public Playlist(  ){
	    this.name = " ";
		this.description = null;
	    this.songList = new Song[ 100 ];
	    this.totalSongs = 0;
  	}

  	public Playlist( String name, Song [] songList ){
  		this.name = name;
  		this.description = null;
	    this.songList = new Song[ 100 ];
	    this.totalSongs = 0;
  	}

  	//Setters
  	public void setName( String name ){
  		this.name = name;
  	}

  	public void setDescription( String description ){
  		this.description = description;
  	}

  	public void addSong( String name, String artist, 
		String album, String length ){
  		Song song = new Song( name, artist, album, length );

  		songList[ this.totalSongs ] = song;
  		this.totalSongs = this.totalSongs + 1;
  	}


  	//Getters
  	public String getName( ){
  		return this.name;
  	}

  	public String getDescription( ){
  		return this.description;
  	}

  	public Song [] getSongList( ){
  		return this.songList;
  	}

  	public int getTotalSongs( ){
  		return this.totalSongs;
  	}

  	//PRINT STATEMENT
  	public void viewPlaylist( ){
	    System.out.format("+-----------------------------+-------------------------+------------+------+%n");
	    System.out.format("| # | TITLE                   | ALBUM                   | TIME              |%n");
	    System.out.format("+-----------------------------+-------------------------+------------+------+%n");
	    for( int songIdx = 0; songIdx < totalSongs; songIdx++ ){
	        System.out.printf("| %-2d| %-24s| %-24s| %-11s| %-5s|\n", (songIdx+1), songList[songIdx].getName(), 
			songList[songIdx].getAlbum(), songList[songIdx].getLength() );
	        System.out.printf("| %-2s| %-24s| %-24s| %-11s| %-5s|\n", "", songList[songIdx].getArtist(), "", "");
	    }
	    System.out.format("+-----------------------------+-------------------------+------------+------+%n");
	}  

}//END CLASS