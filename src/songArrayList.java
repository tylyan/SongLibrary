import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/* song storage + read/write to text file*/

public class songArrayList{
	
	public JList<String> mainList = new JList<String>();
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
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
	
	public void load(String file) throws IOException{ // load songs from text into array list
		this.songFile = new File(file);
		Scanner sc = new Scanner(songFile);
		
		while(sc.hasNextLine()){
			
			String[] line = sc.nextLine().split("\\|");
			String name = line[0];
			String artist = line[1];
			Song song = new Song();
			
			song.setName(name);
			song.setArtist(artist);
			
			if (line.length == 3){
				String album = line[2];
				song.setAlbum(album);
				
			}
			
			if(line.length == 4){
				String year = line[3];
				song.setYear(year);
			}
			
			
			add(song);
		}
		for (int i = 0; i < songList.size(); i++){
			System.out.println(songList.get(i).getName() + " by " + songList.get(i).getArtist());
		}
		
		sc.close();
		mainList = new JList<String>(listModel);
		mainList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		mainList.setLayoutOrientation(JList.VERTICAL);
		
	}
	
	
	public void add(Song s){ //add song to correct location in List (Alphabetized)
		
		int i = 0;
		
		//find index to add
		for(i=0; i < songList.size(); i++){
			if (s.getName().compareTo(songList.get(i).getName()) <= 0) 
				break;
		}
		//same song name, sort by artist
		if (songList.isEmpty()){
			songList.add(s);
			listModel.add(i, s.getName());
		}else if (i == songList.size()){
			songList.add(s);
			listModel.add(i, s.getName());
		}else if(s.getName().compareTo(songList.get(i).getName())==0){
			if (s.getArtist().compareTo(songList.get(i).getArtist()) > 0){
				i++;
			}
		}
		//add song only if not duplicate
		if(!(s.getArtist().compareTo(songList.get(i).getArtist())==0 && s.getName().compareTo(songList.get(i).getName())==0)){
			songList.add(i,s);
			listModel.add(i, s.getName());
		}
	}
	
	//returns song information via index number
	public Song getSong(int index){
		return songList.get(index);
	}
}
