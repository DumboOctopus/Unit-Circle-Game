import javax.swing.*;

/**
 * Created by Neily on 11/25/15.
 */
public class SettingsEvent {
    private UnitCircleWindow window;
    public SettingsEvent(UnitCircleWindow window)
    {
        this.window = window;
    }

    public Settings getSettings() {
        return window.getSettings();
    }
    public JButton getMainButton()
    {
        return window.getMainButton();
    }
    public JLabel getFeedbackLabel()
    {
        return window.getFeedbackLabel();
    }
    public void hideQuestion(){
        window.hideQuestion();
    }
    public void nextQuestion()
    {
        window.nextQuestion();
    }
    public boolean isMode(int mode){
        return(mode == window.getSettings().getCurrMode());
    }
}
