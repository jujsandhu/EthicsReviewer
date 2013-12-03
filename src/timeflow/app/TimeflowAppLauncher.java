package timeflow.app;

import timeflow.format.file.FileExtensionCatalog;
import timeflow.format.file.TimeflowFormat;
import timeflow.model.*;

import javax.swing.*;
import java.awt.event.*;

// For some reason we have to do this in a separate class in order to
// get the menubar working right on the Mac.

public class TimeflowAppLauncher {
	public static void launch(String fileName)
	{
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "TimeFlow");
		System.out.println("Running "+Display.version());
		
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (Exception e) {
	       System.out.println("Can't set system look & feel");
	    }		
		
		final TimeflowApp t=new TimeflowApp();
	    final String file = fileName;
	    
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try
				{
					t.init();
					t.setVisible(true);
					t.load(file, FileExtensionCatalog.get(file), true);
				}
				catch (Exception e)
				{
					e.printStackTrace(System.out);
				}
				
				/*
				t.splash.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						t.splash.setVisible(false);
					}}
				);
				*/
				//t.splash(false);
				//t.splash.message=t.model.getDisplay().version();
			}});
		
	}
}
