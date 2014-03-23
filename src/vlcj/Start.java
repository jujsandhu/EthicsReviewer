package vlcj;

import java.io.File;

import javax.swing.JFileChooser;

public class Start {
	
	//jfilechooser help us select which file we need
	private static JFileChooser ourFileSelector = new JFileChooser();

	public static void main(String[] args){
		
		//initialize variables
		String vlcPath = "", mediaPath="";
		File ourFile;
		
		//Set the file selection mode to directories only
		ourFileSelector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		//open jfilechooser
		ourFileSelector.showSaveDialog(null);
		
		//get file from jfielchooser
		ourFile = ourFileSelector.getSelectedFile();
		
		//get folderpath of file
		vlcPath = ourFile.getAbsolutePath();
		
		
		//Selt file selection mode to files only
		ourFileSelector.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		//open jfilechooser
		ourFileSelector.showSaveDialog(null);
		
		//get file
		ourFile = ourFileSelector.getSelectedFile();
		
		//get media path from the file
		mediaPath = ourFile.getAbsolutePath();
		
		//Instantiate mediaplayer, pass mediaplayer object, pass vlcpath media path and invoke run
		new MediaPlayer(vlcPath, mediaPath).run();
		
	}
	
	
	
}
