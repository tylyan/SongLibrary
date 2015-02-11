import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;




public class songLib extends JFrame implements ActionListener{
	
	JPanel listPanel, detailPanel, editPanel, statusPanel;
	JLabel songs, description, userEdit;
	JList list;
	songArrayList sL = new songArrayList();
	


	public songLib(String title) throws IOException  {
		super(title);
		sL.load();
		listpanel();
		
	}
	
	public void listpanel(){
		JList list = new JList();
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
	public static void main(String[] args){
		
	}

}

