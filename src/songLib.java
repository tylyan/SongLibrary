import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class songLib extends JFrame implements ActionListener{
	
	public static JPanel listPanel = new JPanel();
	public static JPanel detailPanel = new JPanel();
	public static JPanel editPanel = new JPanel();
	public static JPanel statusPanel = new JPanel();
	public static JLabel songs, description, userEdit;
	public static JLabel songLabel, artistLabel, albumLabel, yearLabel;
	songArrayList sL = new songArrayList();


	public songLib(String title) throws IOException  {
		super(title);
		sL.load("songs.txt");
		listpanel();
		detailpanel();
		
	}
	
	public void listpanel(){
		songs = new JLabel("Songs");
		songs.setOpaque(true);
		listPanel.add(songs);
		listPanel.add(sL.scrollPane);
	}
	
	public void detailpanel(){
		
		Song selected_song = sL.getSong(sL.mainList.getSelectedIndex());
		songLabel = new JLabel("Song:");
		artistLabel = new JLabel("Artist:");
		albumLabel = new JLabel("Album: ");
		yearLabel = new JLabel("Year: ");
		detailPanel.add(songLabel);
		detailPanel.add(artistLabel);
		detailPanel.add(albumLabel);
		detailPanel.add(yearLabel);
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
	public static void main(String[] args) 
			throws IOException{
		JFrame mainWindow= new songLib("My Library");
		mainWindow.setLayout(new FlowLayout());
		mainWindow.setResizable(false);
		mainWindow.add(listPanel);
		mainWindow.add(detailPanel);
		mainWindow.add(editPanel);
		mainWindow.add(statusPanel);

		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setSize(350, 500);
		mainWindow.setLocationRelativeTo(null);
		listPanel.setPreferredSize(new Dimension(150, 480));
		listPanel.setSize(new Dimension(150, 480));
		mainWindow.setVisible(true);
		;
		
	};

}

