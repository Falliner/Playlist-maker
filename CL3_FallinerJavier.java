import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
/* ----------------------------------- */
/*
	Javier Falliner [CS1101 - FA22] Comprehensive Lab 3 This work is to be done individually. 
	It is not permitted to share, reproduce, or alter any part of this assignment for any purpose.			
	Students are not permitted from sharing code, uploading this assignment online in any form, 
	or viewing/receiving/modifying code written from anyone else.
	This assignment is part of an academic course at The University of Texas at El Paso 
	and a grade will be assigned for the work produced individually by the student. 
*/
public class CL3_FallinerJavier{
	public static void main(String [] args){
		int menu = 0;
		Scanner userInput = new Scanner(System.in);
		String playlistNamer = " "; 
		int choice = 0;
		FileReader method = new FileReader("song-directory.csv");
		while(menu != 5){
			System.out.println("What would you like to do?");
			System.out.println("1. Create new playlist\n" + 
				"2. Add a song to playlist\n" +
				"3. View playlist\n" + 
				"4. Delete playlist\n" +
				"5. Close application");
			menu = userInput.nextInt();
			if(menu == 1){
				System.out.println("What is the name of your new playlist?"); //creates playlist succesfully
			    playlistNamer = userInput.next();
			    method.createPlaylist(playlistNamer);
			}
			else if(menu == 2){ //adds song and checks if it exists otherwise it does nothing, only thing wrong with mine is that it doesn't shows correctly the directory, but it DOES add them
				System.out.println("Which playlist do you want to add the song to: ");
				playlistNamer = userInput.next();
				if(!method.checkPlaylistExistance(playlistNamer)){
				System.out.println("Can't add anything to something that does not exist!");
				}else{
				System.out.println("Look at the song directory available at Spotify.\n" +
				"Enter the number corresponding to the song you would liked to add to " + "'" + playlistNamer + "'" );
				method.displaySongDirectory();
				System.out.println("Song number");
				choice = userInput.nextInt();
				method.addSongToPlaylist(playlistNamer, choice);
				}

			}
			else if(menu == 3){ //Displays playlist, in the sense that it shows that its there and it HAS stuff, if it doesn't it prints out a message to add songs, but doesn't displays correctly the playlist. 
				System.out.println("Which playlist do you want to see?: ");
				playlistNamer = userInput.next();
				if(method.totalSongs(playlistNamer) == 0){
					System.out.println("There are no songs in these, mind adding some songs?");
				}else 
				method.displayPlaylist(playlistNamer);

			}
			else if(menu == 4){ //deletes the whole playlist file like if you were to delete a whole playlist in Spotify
				System.out.println("which playlist do you want to delete?:");
				playlistNamer = userInput.next();
				method.deletePlaylist(playlistNamer);
			}
		}
		if(menu == 5){ //closes the program. 
			System.out.println("Closing Spotify application...");
		}

		}
		

	}
