import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class songLib extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static JPanel listPanel = new JPanel();
	public static JPanel detailPanel = new JPanel();
	public static JPanel statusPanel = new JPanel();
	public static JLabel songs, description, userEdit;
	public static JLabel nameLabel, artistLabel, albumLabel, yearLabel;
	public static JButton addButton, editButton, deleteButton, okButton, cancelButton;
	public static JTextField nameText, artistText, albumText, yearText;
	Song selectedSong;
	songArrayList sL = new songArrayList();

	/**
	 * Constructor 
	 * @param title
	 * @throws IOException
	 */
	public songLib(String title) throws IOException  
	{
		super(title);
		sL.load("songs.txt");
		ListSelectionModel sModel = sL.mainList.getSelectionModel();
		sModel.addListSelectionListener(new Selection());
		listpanel();
		detailpanel();
		attachHandlers();
	}
	
	/**
	 * Creates the list panel
	 */
	public void listpanel()
	{
		songs = new JLabel("Songs");
		songs.setOpaque(true);
		listPanel.add(songs);
		listPanel.add(sL.scrollPane);
	}
	
	/**
	 * Creates the detail panel
	 */
	public void detailpanel()
	{	
		nameLabel = new JLabel("Song:");
		artistLabel = new JLabel("Artist:");
		albumLabel = new JLabel("Album: ");
		yearLabel = new JLabel("Year: ");
		
		nameText = new JTextField();
		artistText = new JTextField();
		albumText = new JTextField();
		yearText = new JTextField();
		
		addButton = new JButton("Add");
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
		nameText.setPreferredSize(new Dimension(120,20));
		artistText.setPreferredSize(new Dimension(120,20));
		albumText.setPreferredSize(new Dimension(120,20));
		yearText.setPreferredSize(new Dimension(120,20));
		
		detailPanel.add(nameLabel);
		detailPanel.add(nameText);
		
		detailPanel.add(artistLabel);
		detailPanel.add(artistText);
		
		detailPanel.add(albumLabel);
		detailPanel.add(albumText);
		
		detailPanel.add(yearLabel);
		detailPanel.add(yearText);
		
		detailPanel.add(addButton);
		detailPanel.add(editButton);
		detailPanel.add(deleteButton);
		detailPanel.add(okButton);
		detailPanel.add(cancelButton);
	}
	
	public void attachHandlers(){
		addButton.setActionCommand("Add");
		editButton.setActionCommand("Edit");
		deleteButton.setActionCommand("Delete");
		okButton.setActionCommand("OK");
		cancelButton.setActionCommand("Cancel");
	}
	
	/**
	 * Action Handlers
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add")){
			System.out.println("add clicked");
			if (!(nameText.equals("") && artistText.equals(""))){
				Song nS = new Song();
			}
		}else if (e.getActionCommand().equals("Edit")){
			System.out.println("edit clicked");
		}else if (e.getActionCommand().equals("Delete")){
			System.out.println("delete clicked");
		}else if (e.getActionCommand().equals("OK")){
			System.out.println("ok clicked");
		}else{
			System.out.println("cancel clicked");
		}
		
	}
	
	/**
	 * Main method
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) 
			throws IOException{
		JFrame mainWindow= new songLib("My Library");
		mainWindow.setLayout(new FlowLayout());
		mainWindow.setResizable(false);
		mainWindow.add(listPanel);
		mainWindow.add(detailPanel);
		mainWindow.add(statusPanel);

		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setSize(500, 400);
		mainWindow.setLocationRelativeTo(null);
		detailPanel.setPreferredSize(new Dimension(150, 480));
		listPanel.setPreferredSize(new Dimension(150, 480));
		mainWindow.setVisible(true);
	};
	
	/**
	 * Event handler for list selection, outputs currently selected song's
	 * information onto the detail panel
	 * @author yuky
	 *
	 */
	class Selection implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			int index = e.getFirstIndex();
			selectedSong = sL.getSong(index);
			/*
			selectedSong.getName();
			selectedSong.getArtist();
			selectedSong.getAlbum();
			selectedSong.getYear();
			*/
		}
	}

}

