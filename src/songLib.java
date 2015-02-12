import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;




public class songLib extends JFrame implements ActionListener{
	
	JPanel listPanel = new JPanel();
	JPanel detailPanel = new JPanel();
	JPanel editPanel = new JPanel();
	JPanel statusPanel = new JPanel();
	JLabel songs, description, userEdit;
	songArrayList sL = new songArrayList();


	public songLib(String title) throws IOException  {
		super(title);
		sL.load("songs.txt");
		listpanel();
		
	}
	
	public void listpanel(){
		listPanel.add(sL.mainList);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
	public static void main(String[] args) 
			throws IOException{
		songLib mainWindow= new songLib("My Library");
		
		
	};

}

