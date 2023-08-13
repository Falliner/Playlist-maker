import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class FileReader {
    //Declare attribute
    private File songDirectory = new File ("song-directory.csv"); 
    private String fileName = "song-directory";  //made it this way because for some reason it wouldn't just read this.songDirectory 

    //  CONSTRUCTOR:
         //Takes no parameters and sets "song-directory.csv" to attribute
    public FileReader ( String fileName ){
        this.fileName = fileName;
        this.songDirectory = songDirectory; 
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - CREATE SONG
		The method below will take a line from a file as a parameter,
		and turn that line into a song object and return it.
		This line must be split before as such:

			token[0] = Name of song
			token[1] = Name of artist
			token[2] = Name of album
			token[3] = Length of the song

        This is because in your file, your songs are viewed like this:
        Daylily,movements,Feel Something,3:28

        @param line: represents a line read from a file
        @return the line converted into a song object

        METHODS USED:
            - split()
	*/
    private Song createSong( String line ){
        //Change code as instructed
        File file = new File(this.fileName);
        Scanner readFile = null;
        String currLine = null;             //manage to reduce the amount of code and it still works as it should
        Song song = null;
        try{
            readFile = new Scanner ( file );
            readFile.nextLine();
            while (readFile.hasNext()) {
                currLine = readFile.nextLine();
                String[] splitCommand = currLine.split(",");
                song = new Song(splitCommand[0], splitCommand[1], splitCommand[2], splitCommand[3]);
            }

            readFile.close();
            return song; 
        }catch(IOException errMsg){
            System.out.println("couldn't read the file");
        }
        return song; 
	}

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - CREATE PLAYLIST
		The method will take a playlistName and create a new file.
        Print true if successful, false otherwise

        1. Check if the playlist already exists. If it does, exit the method
        2. If the playlist does not exist, create it by using:
            - createNewFile()       HINT: This method only exists in the File class

        @param playlistName: the name of the playlist given by the user

        METHODS USED:
            - exists()
            - createNewFile()
	*/
	public void createPlaylist( String playlistName ){
        if(checkPlaylistExistance(playlistName)){
            System.out.println("This playlist already exists!");
            return; 
        }
        File playlistCreator = new File(playlistName + ".txt"); 
            try {
                playlistCreator.createNewFile();
            } catch (IOException error){
                System.out.println("There was an error");
                }
                System.out.println("Playlist " + playlistName + " succesfully created");
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ADD SONG TO PLAYLIST
		The method will take a song choice, which is the number of the song
		from the file as well as the name of the playlist, and will store 
		its information into the given playlists text file.

        1. If the file does not exist, exit the method and return false
        2. If the file exists, traverse through this.songDirectory, you will traverse it
        songChoice number of times. Once songChoice is equal to the line you are currently
        in your file, you will save that line (From the file) into the text file of the
        playlistName.

        @param playlistName: the name of the playlist given by the user
        @param songChoice: the number of the song the user chose to save to their playlist
        @return true if successful, false otherwise

        METHODS USED:
            - exists()
	*/
	public boolean addSongToPlaylist( String playlistName, int songChoice ){
        File file = new File(this.fileName);
        if(!file.exists()){
            return false;
        }
        Scanner readFile = null;
        String line = null;
        int choice = 0;
        PrintWriter pw = null;
        String playlistFile = playlistName + ".txt";
        try{ 
            readFile = new Scanner ( this.songDirectory );
            readFile.nextLine();
            while(readFile.hasNext()){
                choice++;
                readFile.nextLine();
            if(choice == songChoice){
                pw = new PrintWriter( new BufferedWriter( new FileWriter (playlistFile, true) ) );
                pw.println(readFile.nextLine());
                pw.close();
                }
            }
        }catch(IOException errorMsg){
            System.out.println("There was an error");
        }
        System.out.println("Song has been added to: " + playlistName + " !");
        return true; 
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - CHECK PLAYLIST EXISTANCE
		The method will take the name of a playlist and check if it exists
		or not. Return true if it exists, false otherwise. 

        IMPORTANT: This method is exclusively for the main to use it.

        1. If the playlist already exists, return true
        2. False otherwise

        @param playlistName: the name of the playlist given by the user

        METHODS USED:
            - exists()
	*/
	public boolean checkPlaylistExistance( String playlistName ){
        File playlist = new File (playlistName + ".txt");
        if(playlist.exists()){
            return true;
        }
        return false;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - DISPLAY SONG DIRECTORY
		This method will traverse through this.songDirectory and convert each 
        line into a song object, and print the song object in a nice output as
        shown on your comprehensive lab 3 instruction document.

        1. If the playlist does not exist, exit the method (return)
        2. If the playlist exists, you must convert each line into a song object
        and print it out in a nice and readable format.

        METHODS USED:
            File Class Method
                - exists( )
            FileReader Class Method
                - createSong( )
            Song Class Methods
                - getName( )
                - getAlbum( )
                - getLength( )
                - getArtist( )
	*/
	public void displaySongDirectory( ){
        // USE THE CODE BELOW IN THE CORRECT PLACES
        // Only thing you need to fix is to fill in the [?] blocks. Leave everything else the same :)
            File file = new File(this.fileName); 
            if(!file.exists()){
                return; 
            }
            System.out.format("+---------------------------------+---------------------------+------+%n");
			System.out.format("| # | TITLE                       | ALBUM                     | TIME |%n");
			System.out.format("+---------------------------------+---------------------------+------+%n");
             try (Scanner readFile = new Scanner(file)) {
        readFile.nextLine(); // Skip the header line
        int songNumber = 1;
        
        while (readFile.hasNextLine()) {
            String line = readFile.nextLine();
            String[] splitCommand = line.split(",");
            
            String name = splitCommand[0];
            String artist = splitCommand[1];
            String album = splitCommand[2];
            String length = splitCommand[3];
            
            System.out.printf("| %-2d| %-28s| %-26s| %-5s|\n", songNumber, name, album, length);
            System.out.printf("| %-2s| %-28s| %-26s| %-5s|\n", "", artist, "", "");
            System.out.format("+---------------------------------+---------------------------+------+%n");
            
            songNumber++;
        }
    } catch (IOException errMsg) {
        System.out.println("Couldn't read the file");
    }
    }


    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - TOTAL SONGS
        This method will traverse through a given playlist and return the total number
        of songs in that file. This will help set up the size of the songList in the
        Playlist class!

        1. If the file does not exist, exit the method (by returning 0)
        2. If it does exist, traverse through the playlist file and count the number
        of lines that exist within that file (same as your CL2)
        
        @param playlistName: the name of the playlist given by the user
     */
    public int totalSongs( String playlistName ){
        int count = 0; 
        if(!checkPlaylistExistance(playlistName)){  //this one is just like in CL2 its gonna count the lines and that will total all of the songs. 
        return 0;
        }
        if(checkPlaylistExistance(playlistName)) {
            try{ Scanner playlistLines = new Scanner ( new File (playlistName + ".txt ") );
            while(playlistLines.hasNext()){
                count++; 
                playlistLines.nextLine();
            }
            return count; 
            }catch(IOException msg){
                System.out.println("There was an error");
            }
        }
        return 0; 
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - READ PLAYLIST
		This method reads the playlist text file of any playlist specified, converts
        each line into a Song object, and stores it into a Song array.
        Lastly, it will return an array of Song

        @param playlistName: the name of the playlist given by the user
        @return an array of Song
        
        METHODS USED:
            - totalSongs()
            - createSong()
	*/
	public Song [] readPlaylist( String playlistName ){
        //Change the code below as needed
        File file = new File(playlistName + ".txt");
        Song [] songList = null; 
        int i = 0;

        if(!checkPlaylistExistance(playlistName)){
        System.out.println("There is no playlist to read!");
        return songList;
        }

        try{
        Scanner fileReader = new Scanner( file ); 
        while(fileReader.hasNext()){
            songList = new Song[100];
            songList[i] = createSong(playlistName);
            i++;
             return songList; 
        }
        }catch(IOException errorMsg){
        System.out.println("There was an error");
        }
        return songList; 
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - DISPLAY PLAYLIST
		This method will traverse through a specified playlist, turn it into a Playlist
		object, and display the playlist in a nice format.

        1. If the playlist file does not exist, exit the method (Return)
        2. If it does exist, create a new Playlist object and send over the name
        of the playlist as well as an array of Song to be set to the object.

        @param playlistName: the name of the playlist given by the user
        
        METHODS USED
            - exists()
            - readPlaylist()
            - viewPlaylist() --> from the Playlist class
	*/
	public void displayPlaylist( String playlistName ){
        if(!checkPlaylistExistance(playlistName)){
            System.out.println("Can't show something that does not exist!");
            return; 
        }//for some reason this one WORKS
            Playlist playlist = new Playlist(playlistName, readPlaylist(playlistName));
            playlist.viewPlaylist();     
        
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - DELETE PLAYLIST
		This method deletes the given playlist by the user.
		It will take a playlistName as a parameter and be used to remove it,
		if and only if it already exists. Return true if successful, false otherwise.

        1. If the file does not exist, exit the method. You cannot delete a file
        that does not exist.

        @param playlistName: the name of the playlist given by the user
        @return true if successful, false otherwise

        METHODS USED:
            - exists()
            - delete() --> A new method from the File class (Google File Oracle)
	*/
	public boolean deletePlaylist( String playlistName ){
       if(!checkPlaylistExistance(playlistName)){
        System.out.println("You can't delete something that doesn't exists!");
        return false;
        }
        if(checkPlaylistExistance(playlistName)){
            File playlistFile = new File (playlistName + ".txt");
            if(playlistFile.delete()){ //this will delete the whole file, couldn't find a way to only delete what's inside of it, probably with printwriter is my guess to do a single line
                System.out.println("The playlist: " + playlistName + " has been deleted");
            }else{
                System.out.println("Failed to delete the playlist");
            } 
        } 
        return true;    
    }   
}