package ethicsreviewer.main;

import javax.swing.SwingUtilities;

import ethicsreviewer.views.InitialView;

public class Main {

	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run()
            {
                new InitialView().setVisible(true);
            	//new lecturerLogin().setVisible(true);
            }

        });
        
        
	}

}