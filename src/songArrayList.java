import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/*
 * Yuk Yan
 * Derek Wong
 * 
 */


/* song storage management + read/write to text file*/
public class songArrayList{
	
	public JList<String> mainList = new JList<String>();
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private File songFile;
	ArrayList<Song> songList = new ArrayList<Song>();
	JScrollPane scrollPane = new JScrollPane();
	
	public songArrayList(){
		
	}
	
	
	public void save() throws IOException{ // save array list to text file
		
		FileWriter f = new FileWriter("songs.txt");
		StringBuilder string = new StringBuilder();
		
		for(int i=0; i<songList.size();i++){
			Song s = songList.get(i);
			
			string.append(s.getName()+"|"+s.getArtist()+"|"+s.getAlbum()+"|"+s.getYear()+"\n");
			
		}
		
		f.write(string.toString());
		f.close();
		
	}
	
	public void load(String file) throws IOException{ // load songs from text into array list
		this.songFile = new File(file);
		
		songFile.createNewFile();
		
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
				String album = line[2];
				song.setAlbum(album);
				String year = line[3];
				song.setYear(year);
			}
			
			
			add(song);
		}
		
		sc.close();
		mainList = new JList<String>(listModel);
		mainList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		mainList.setLayoutOrientation(JList.VERTICAL);
		mainList.setSelectedIndex(0);
		scrollPane.setViewportView(mainList);

	}
	
	
	public void add(Song s){ //add song to correct location in List (Alphabetized)
		
		
		int i=0;
		String songName = s.getName().toLowerCase();
		String songArtist = s.getArtist().toLowerCase();
		
		//find index to add
		for(; i < songList.size(); i++){
			if (songName.compareTo(songList.get(i).getName().toLowerCase()) < 0){
				break;
			}else if(songName.compareTo(songList.get(i).getName().toLowerCase()) == 0){
				if(songArtist.compareTo(songList.get(i).getArtist().toLowerCase()) < 0){
					break;
				}
			}
				
		}
		
		if (songList.isEmpty()){
			songList.add(s);
			listModel.add(i, s.getName());
			
		}else if (i == songList.size()){
			songList.add(s);
			listModel.add(i, s.getName());
		}else{
		
			
				songList.add(i,s);
				listModel.add(i,s.getName());
			
			
		}
	}
	
	//returns song information via index number
	public Song getSong(int index){
		return songList.get(index);
	}
	
	public void setSong(int index,String name, String artist, String album, String year){
		//set changes in songList
		Song selectedSong = songList.get(index);
		selectedSong.setName(name.trim());
		selectedSong.setArtist(artist.trim());
		selectedSong.setAlbum(album.trim());
		selectedSong.setYear(year.trim());
		
		//set changes in ListModel
		listModel.set(index, name.trim());
		
	}
	
	public void deleteSong(int index){
		songList.remove(index);
		listModel.remove(index);
	}
	
	/*Returns true when Song Name + Artist Match (Not case sensitive) */ 
	public boolean checkDuplicate(Song s){
		for(int i=0; i<songList.size();i++){
			if(songList.get(i).getName().toLowerCase().compareTo(s.getName().toLowerCase())==0){
				if(songList.get(i).getArtist().toLowerCase().compareTo(s.getArtist().toLowerCase())==0){
					return true;
				}
			}
		}
		
		return false;
	}
	/*Checks for duplicates excluding original name+artist since we are editing it*/
	public boolean checkDuplicateEdit(Song orig, Song edited){
		
		//same name+artist so edit is not in name or artist field
		if(orig.getName().toLowerCase().compareTo(edited.getName().toLowerCase())==0){ 
			if(orig.getArtist().toLowerCase().compareTo(edited.getArtist().toLowerCase())==0){
				return false;
			}
			
		}
		
		for(int i=0; i<songList.size();i++){
			if(songList.get(i).getName().toLowerCase().compareTo(edited.getName().toLowerCase())==0){
				if(songList.get(i).getArtist().toLowerCase().compareTo(edited.getArtist().toLowerCase())==0){
					return true; //sound was edited into another song already in the library
				}
			}
		}
		
		return false; //unique name+artist
	}
}
