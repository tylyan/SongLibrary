import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/* song storage + read/write to text file*/

public class songArrayList {
	
	private File songFile;
	ArrayList<Song> songList = new ArrayList<Song>();
	
	public songArrayList(){
		
	}
	
	
	public void save() throws IOException{ // save array list to text file
		
		FileWriter f = new FileWriter(this.songFile);
		
		for(int i=0; i<songList.size();i++){
			Song s = songList.get(i);
			String song = s.getName();
			song.concat("|"+ s.getArtist());
			song.concat("|"+ s.getAlbum());
			song.concat("|"+ s.getYear()+"\n");
			f.write(song);
			
		}
		
		f.close();
		
	}
	
	public void load() throws IOException{ // load songs from text into array list
		this.songFile = new File("songs.txt");
		Scanner sc = new Scanner(songFile);
		
		while(sc.hasNextLine()){
			
			String[] line = sc.nextLine().split("\\|");
			String name = line[0];
			String artist = line[1];
			Song song = new Song();
			
			song.setName(name);
			song.setArtist(artist);
					
			if(line[2]!= ""){
				String album = line[2];
				song.setAlbum(album);
			}
			
			if(line[3]!= ""){
				String year = line[3];
				song.setYear(year);
			}
			
			
			add(song);
			
		}
		
		sc.close();
		
		
	}
	
	public void add(Song s){ //add song to correct location in List (Alphabetized)
		
		if(songList.isEmpty()){
			songList.add(s);
			return;
		}
		
		for(int i=0; i<songList.size(); i++){
			
			if(s.getName().compareTo(songList.get(i).getName())<=0){
				songList.add(i,s);
				return;
			}
		}
		
		songList.add(s);

	}
}
