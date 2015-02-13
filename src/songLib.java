import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * Yuk Yan
 * Derek Wong
 * 
 */

public class songLib extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static JPanel listPanel = new JPanel();
	public static JPanel detailPanel = new JPanel();
	public static JPanel statusPanel = new JPanel();
	public static JLabel songs, status, statusText;
	public static JLabel nameLabel, artistLabel, albumLabel, yearLabel;
	protected static JButton addButton, editButton, deleteButton, confirmButton, cancelButton;
	protected static JTextField nameText, artistText, albumText, yearText;
	Song selectedSong;
	static songArrayList sL = new songArrayList();
	ListSelectionModel sModel;
	static String action = "";
	
	/**
	 * Constructor 
	 * @param title
	 * @throws IOException
	 */
	public songLib(String title) throws IOException 
	{
		super(title);
		sL.load("songs.txt");
		sModel = sL.mainList.getSelectionModel();
		listpanel();
		detailpanel();
		statuspanel();
		attachHandlers();
		
		
		/* Initially set first song into details if not empty*/
		if(!sL.songList.isEmpty()){
			nameText.setText(sL.getSong(0).getName());
			artistText.setText(sL.getSong(0).getArtist());
			albumText.setText(sL.getSong(0).getAlbum());
			yearText.setText(sL.getSong(0).getYear());
		}
	}
	
	/**
	 * Creates the list panel
	 */
	public void listpanel()
	{
		songs = new JLabel("Songs");
		songs.setOpaque(true);
		sL.scrollPane.setPreferredSize(new Dimension(180,280));
		listPanel.add(songs);
		listPanel.add(sL.scrollPane);
		
	}
	
	/**
	 * Creates the detail panel
	 */
	public void detailpanel()
	{	
		detailPanel.setLayout(new BoxLayout(detailPanel,BoxLayout.Y_AXIS));
		
		nameLabel = new JLabel("Title:");
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
		confirmButton = new JButton("Confirm");
		cancelButton = new JButton("Cancel");
		
		nameText.setPreferredSize(new Dimension(120,20));
		artistText.setPreferredSize(new Dimension(120,20));
		albumText.setPreferredSize(new Dimension(120,20));
		yearText.setPreferredSize(new Dimension(120,20));
		
		nameText.setEnabled(false);
		artistText.setEnabled(false);
		albumText.setEnabled(false);
		yearText.setEnabled(false);
		
		nameText.setDisabledTextColor(Color.BLACK);
		artistText.setDisabledTextColor(Color.BLACK);
		albumText.setDisabledTextColor(Color.BLACK);
		yearText.setDisabledTextColor(Color.BLACK);
		
		confirmButton.setEnabled(false);
		cancelButton.setEnabled(false);
		
		
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
		detailPanel.add(confirmButton);
		detailPanel.add(cancelButton);
		
		
	}
	
	public void statuspanel(){
		status = new JLabel("=====Status=====");
		statusText = new JLabel();
		statusText.setSize(180, 40);
		
		statusPanel.setLayout(new BorderLayout());
		status.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
		statusText.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
		statusPanel.add(status, BorderLayout.CENTER);
		statusPanel.add(statusText, BorderLayout.PAGE_END);
		
		
	}
	
	public void updateStatus(String status){
		statusText.setText(status);
	}
	
	/**
	 * Attaches action handler to all the buttons
	 */
	public void attachHandlers()
	{
		addButton.addActionListener(this);
		editButton.addActionListener(this);
		deleteButton.addActionListener(this);
		confirmButton.addActionListener(this);
		cancelButton.addActionListener(this);
		sModel.addListSelectionListener(new Selection());
	}
	
	/**
	 * Action Handlers
	 * @param e
	 */
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getActionCommand().equals("Add")){
			updateStatus("Song Title and Artist required. Press CONFIRM to save.");
			action = "add";
			
			//set text fields empty
			nameText.setText("");
			artistText.setText("");
			albumText.setText("");
			yearText.setText("");
			
			//enable text fields + confirm/cancel
			nameText.setEnabled(true);
			artistText.setEnabled(true);
			albumText.setEnabled(true);
			yearText.setEnabled(true);
			
			confirmButton.setEnabled(true);
			cancelButton.setEnabled(true);
			
			//disable add/edit/delete
			addButton.setEnabled(false);
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
			
			
		}else if (e.getActionCommand().equals("Edit")){
			//edit stuff
			updateStatus("Editing details. Press CONFIRM to save.");
			action = "edit";
			//enable text fields + confirm/cancel
			nameText.setEnabled(true);
			artistText.setEnabled(true);
			albumText.setEnabled(true);
			yearText.setEnabled(true);
			
			confirmButton.setEnabled(true);
			cancelButton.setEnabled(true);
			
			//disable add/edit/delete
			addButton.setEnabled(false);
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
			
		}else if (e.getActionCommand().equals("Delete")){
			updateStatus("Press CONFIRM to delete selected song.");
			action = "delete";
			//enable confirm/cancel
			confirmButton.setEnabled(true);
			cancelButton.setEnabled(true);
			
			//disable add/edit/delete
			addButton.setEnabled(false);
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
			
		}else if (e.getActionCommand().equals("Confirm")){
			//confirm button
			if (action.equals("add")){
				Song tempSong = new Song(nameText.getText().trim(),artistText.getText().trim(),albumText.getText().trim(),yearText.getText().trim());
				//check for required name and artist
				if(nameText.getText().trim().compareTo("")==0||artistText.getText().trim().compareTo("")==0){
					updateStatus("Song title and Artist are required.");
					//cancels
					displaySelected();
					
				}else if(sL.checkDuplicate(tempSong)){
					//duplicate
					updateStatus("Song is already in the library.");
					//cancels
					displaySelected();
					
				}else{
					//actually add stuff
					sL.add(tempSong);
					//select added song + display info
					sL.mainList.setSelectedIndex(sL.songList.indexOf(tempSong));
					displaySelected();
					updateStatus("Song has been successfully added!");
					
				}
				
				//disable text fields
				nameText.setEnabled(false);
				artistText.setEnabled(false);
				albumText.setEnabled(false);
				yearText.setEnabled(false);
				
				//disable confirm/cancel
				confirmButton.setEnabled(false);
				cancelButton.setEnabled(false);
				
				//enable add/edit/delete
				addButton.setEnabled(true);
				editButton.setEnabled(true);
				deleteButton.setEnabled(true);	
					
			}else if (action.equals("edit")){
				Song tempSong = new Song(nameText.getText().trim(),artistText.getText().trim(),albumText.getText().trim(),yearText.getText().trim());
				//get current selected song index
				int minIndex = sModel.getMinSelectionIndex();
	            int maxIndex = sModel.getMaxSelectionIndex();
	            int index=0;
	            for (index = minIndex; index <= maxIndex; index++) {
	                if (sModel.isSelectedIndex(index)) {
	                    break;
	                }
	            }
				//check if empty Name and Artist
				if(nameText.getText().trim().compareTo("")==0||artistText.getText().trim().compareTo("")==0){
					updateStatus("Song title and artist are required.");
					//cancels
					displaySelected();
					
				}else if(sL.checkDuplicateEdit(sL.getSong(index),tempSong)){
					updateStatus("Song is already in the library.");
					//cancels
					displaySelected();
					
				}else{
					/* actually edit stuff*/
		            //edit the song in both arrays
					sL.setSong(index,nameText.getText(),artistText.getText(),albumText.getText(),yearText.getText());
					updateStatus("Song details has been successfully changed!");
				}
				
				//disable text fields
				nameText.setEnabled(false);
				artistText.setEnabled(false);
				albumText.setEnabled(false);
				yearText.setEnabled(false);
				
				//disable confirm/cancel
				confirmButton.setEnabled(false);
				cancelButton.setEnabled(false);
				
				//enable add/edit/delete
				addButton.setEnabled(true);
				editButton.setEnabled(true);
				deleteButton.setEnabled(true);

				
			}else if (action.equals("delete")){
				/*actually delete stuff*/
				//get current selected song
				int minIndex = sModel.getMinSelectionIndex();
	            int maxIndex = sModel.getMaxSelectionIndex();
	            int index=0;
	            for (index = minIndex; index <= maxIndex; index++) {
	                if (sModel.isSelectedIndex(index)) {
	                    break;
	                }
	            }
	            
	            //deleted from both arrays
	            sL.deleteSong(index);
	            
	            //update selected song display if list not empty
	            if(!sL.songList.isEmpty()){
		            sModel.setSelectionInterval(index, index);
					selectedSong = sL.getSong(index);		
					
					nameText.setText(selectedSong.getName());
					artistText.setText(selectedSong.getArtist());
					albumText.setText(selectedSong.getAlbum());
					yearText.setText(selectedSong.getYear());
	            }

				

	            
				//disable confirm/cancel
				confirmButton.setEnabled(false);
				cancelButton.setEnabled(false);
				
				//enable add/edit/delete
				addButton.setEnabled(true);
				editButton.setEnabled(true);
				deleteButton.setEnabled(true);
				
				updateStatus("Song has been successfully deleted!");
				
			}
			
			
		}else{
			//cancel . revert back to song display
			//disable text fields + confirm/cancel
			displaySelected();
			
			nameText.setEnabled(false);
			artistText.setEnabled(false);
			albumText.setEnabled(false);
			yearText.setEnabled(false);
			
			confirmButton.setEnabled(false);
			cancelButton.setEnabled(false);
			
			//enable add/edit/delete
			addButton.setEnabled(true);
			editButton.setEnabled(true);
			deleteButton.setEnabled(true);
			
			updateStatus("Canceled");;
		}
		
	}
	
	/**
	 * Event handler for list selection, outputs currently selected song's
	 * information onto the detail panel
	 * 
	 */
	
	class Selection implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{	
			if(!sL.songList.isEmpty()){
				displaySelected();
			}
			
		}
	}
	
	public void displaySelected(){
			//find correct current index
			int minIndex = sModel.getMinSelectionIndex();
            int maxIndex = sModel.getMaxSelectionIndex();
            int index=0;
            for (index = minIndex; index <= maxIndex; index++) {
                if (sModel.isSelectedIndex(index)) {
                    break;
                }
            }
			selectedSong = sL.getSong(index);
			
			nameText.setText(selectedSong.getName());
			artistText.setText(selectedSong.getArtist());
			albumText.setText(selectedSong.getAlbum());
			yearText.setText(selectedSong.getYear());
	}
	
	
	/**
	 * Main method
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		JFrame mainWindow= new songLib("My Library");
		mainWindow.setLayout(new FlowLayout());
		mainWindow.setResizable(false);
		mainWindow.add(listPanel);
		mainWindow.add(detailPanel);
		mainWindow.add(statusPanel);
		
		
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setSize(500, 500);
		mainWindow.setLocationRelativeTo(null);
		detailPanel.setPreferredSize(new Dimension(200, 300));
		listPanel.setPreferredSize(new Dimension(200, 300));
		statusPanel.setPreferredSize(new Dimension(450,50));
		mainWindow.setVisible(true);
		mainWindow.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				try {
					sL.save();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		
	}
	

}

