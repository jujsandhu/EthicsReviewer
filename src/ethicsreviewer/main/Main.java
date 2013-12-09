package ethicsreviewer.main;

import javax.swing.SwingUtilities;

import timeflow.format.file.TimeflowFormat;
import ethicsreviewer.views.*;

public class Main {

	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run()
            {
                new initialView().setVisible(true);
            	//new lecturerLogin().setVisible(true);
            }

        });
        
        
	}

}
