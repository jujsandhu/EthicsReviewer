package ethicsreviewer.main;

import timeflow.format.file.TimeflowFormat;
import ethicsreviewer.views.*;

public class Main {

	public static void main(String[] args) {
		
		// launch the sessions Screen
		//Testing commit
		Sessions startSession = new Sessions();
        //startSession.openScreen();
        new ResponseView().openScreen();
	}

}
