import java.io.File;
import java.util.ArrayList;

/**
 * Created by Neily on 11/25/15.
 */
public class Settings {

    //TODO: test the code a bit but otherwise nothing else;

    private ArrayList<SettingsListener> listeners;


    private UnitCircleWindow window;
    public static final int QUIZ_TIME = 1, FREE_PLAY = 0;
    private int currMode;

    private File image;

    private int timeGiven;
    private int numberOfQuestions;
    private boolean correctToMoveOn;

    public Settings(UnitCircleWindow unitCircleWindow)
    {
        listeners = new ArrayList<>();
        currMode = Settings.FREE_PLAY;
        image = null;
        timeGiven = 0;
        numberOfQuestions = 0;
        correctToMoveOn = false;

        window = unitCircleWindow;

    }

    //=============================GETTERS AND SETTERS: )

    public int getCurrMode() {
        return currMode;
    }

    public void setCurrMode(int currMode) {
        this.currMode = currMode;
        fireSettingsChanged();
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
        fireSettingsChanged();
    }

    public int getTimeGiven() {
        return timeGiven;
    }

    public void setTimeGiven(int timeGiven) {
        this.timeGiven = timeGiven;
        fireSettingsChanged();
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
        fireSettingsChanged();
    }

    public boolean isCorrectToMoveOn() {
        return correctToMoveOn;
    }

    public void setCorrectToMoveOn(boolean correctToMoveOn) {
        this.correctToMoveOn = correctToMoveOn;
        fireSettingsChanged();
    }


    //================HANDLES THE LISTENERS AND STUFF
    public void addSettingsListener(SettingsListener l)
    {
        listeners.add(l);
    }
    public void removeSettingsListener(SettingsListener l)
    {
        listeners.remove(l);
    }

    public void fireSettingsChanged()
    {
        //SettingsEvent evt = new SettingsEvent(window);
        for(SettingsListener l: listeners)
        {
            //l.settingsChanged(evt);
            l.settingsChanged();
        }
    }

}
