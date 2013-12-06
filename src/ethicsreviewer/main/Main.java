package ethicsreviewer.main;

import javax.swing.SwingUtilities;

import timeflow.format.file.TimeflowFormat;
import ethicsreviewer.views.*;

public class Main {

	public static void main(String[] args) {
		
		// launch the sessions Screen
		//Testing commit
		//Sessions startSession = new Sessions();
        //startSession.openScreen();
		
		//initialView view = new initialView();
		//view.openScreen();
		
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
