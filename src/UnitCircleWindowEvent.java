import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Neily on 11/16/15.
 */

public class UnitCircleWindowEvent {
    //TODO: have this extend SettingsEvent? BC common methods such as getWindow, getMainButton, etc
    //TODO: rescritions on what can be accessed from window? TO THINK LATR
    private UnitCircleWindow window;

    public UnitCircleWindowEvent(UnitCircleWindow window)
    {
        this.window = window;
    }


    public UnitCircleWindow getWindow(){return this.window;}
    public JButton getMainButton(){return window.getMainButton();}
    public JLabel getFeedbackLabel(){return window.getFeedbackLabel();}
    public Settings getSettings () {return window.getSettings();}
    public JMenuBar getMenuBar() {return window.getJMenuBar();}
    public boolean isMode(int mode){
        return(mode == window.getSettings().getCurrMode());
    }
}
