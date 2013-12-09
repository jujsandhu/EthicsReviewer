package ethicsreviewer.views;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.beans.*;

public abstract class ComponentDependencyHandler implements 
    ActionListener, 
    ChangeListener, 
    ItemListener, 
    ListSelectionListener,
    PropertyChangeListener,
    FocusListener,
    KeyListener
{
    // The control list of events we want to respond to.
    // Just add or remove listener classes and recompile.
    private static Class listener_classes[] = {
        ActionListener.class,
        ItemListener.class,
        ChangeListener.class,
        ListSelectionListener.class,
        PropertyChangeListener.class,
        FocusListener.class,
        KeyListener.class,
    };
    
    /**
     * Constructs a ComponentDependencyHandler with a set of dependent components.
     */
    public ComponentDependencyHandler(Component... dependents) {
        for(Component comp : dependents)
            for(Class cls : listener_classes)
                tryToAddListener(comp, cls);
    }
    
    /**
     * User subclasses only need to implement this method to receive
     * notification of changes to dependent components.
     */
    public abstract void dependencyNotification();

    private void tryToAddListener(Component comp, Class listener_class) {
        String listener_name = listener_class.getName();
        int last_dot = listener_name.lastIndexOf('.');
        listener_name = listener_name.substring(last_dot+1);
        try {
            Method adder = comp.getClass().getMethod("add" + listener_name, listener_class);
            adder.invoke(comp, this);
        }
        catch(NoSuchMethodException nsme){} // getMethod
        catch(IllegalAccessException iae){} // invoke
        catch(InvocationTargetException ite){} // invoke
    }

    // all the listener implementations that all forward notification to subclasses
    public void stateChanged(ChangeEvent e) { dependencyNotification();}
    public void itemStateChanged(ItemEvent e) { dependencyNotification(); }
    public void valueChanged(ListSelectionEvent e) { dependencyNotification(); }
    public void actionPerformed(ActionEvent e) { dependencyNotification(); }
    public void propertyChange(PropertyChangeEvent e) { dependencyNotification(); }
    public void focusLost(FocusEvent e) { dependencyNotification(); }
    public void focusGained(FocusEvent e) { dependencyNotification(); }
    public void keyTyped(KeyEvent e) { dependencyNotification(); }
    public void keyPressed(KeyEvent e) { dependencyNotification(); }
    public void keyReleased(KeyEvent e) { dependencyNotification(); }


    /**
     * A simple example program.
     */
    public static void main(String args[]) {
        // build the components 
        final JLabel
            use_password_label = new JLabel("Use Password"),
            enter_label = new JLabel("Enter Password:"),
            instruction_label = new JLabel("Hit <space> to toggle checkbox");
        final JCheckBox password_checkbox = new JCheckBox();
        final JTextField password_text = new JTextField("");
        final JButton submit_button = new JButton("Submit");
        
        // set initial component states
        enter_label.setVisible(false);
        instruction_label.setVisible(false);
        
        // Now set up the dependencies.
        // This is the meat of the example. Notice that the ComponentDependencyHandler 
        // objects only need to be created in order to work. Each one implements
        // the business logic associated with changes to the states of the
        // component or components given to it's constructor.
        
        // "enter" text visibility depends upon checkbox selection
        new ComponentDependencyHandler(password_checkbox) {
            public void dependencyNotification() {
                enter_label.setVisible(password_checkbox.isSelected());
            }
        };
        // text entry field enabled depends upon checkbox selection
        new ComponentDependencyHandler(password_checkbox) {
            public void dependencyNotification() {
                password_text.setEnabled(password_checkbox.isSelected());
            }
        };      
        // instruction text visibility depends upon checkbox FOCUS
        new ComponentDependencyHandler(password_checkbox) {
            public void dependencyNotification() {
                instruction_label.setVisible(password_checkbox.hasFocus());
            }
        };      
        // "select" button enabling depends upon checkbox selected AND password text
        new ComponentDependencyHandler(password_checkbox, password_text) {
            public void dependencyNotification() {
                submit_button.setEnabled(
                    password_checkbox.isSelected()
                    && password_text.getText().length() > 0
                );
            }
        };
        
        // build and display the dialog
        JPanel first_row = new JPanel();
        first_row.setLayout(new BoxLayout(first_row, BoxLayout.X_AXIS));
        first_row.add(use_password_label);
        first_row.add(password_checkbox);
        first_row.add(enter_label);
        first_row.add(password_text);
        JFrame password_dialog = new JFrame("ComponentDependencyHandler Test");
        Container cont = password_dialog.getContentPane();
        cont.setLayout(new BorderLayout());
        cont.add("North", first_row);
        cont.add("Center", instruction_label);
        cont.add("South", submit_button);
        password_dialog.setSize(300, 100);
        password_dialog.setVisible(true);
        password_dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
