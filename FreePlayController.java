

/**
 * This class manages all things dealing with free play mode. The functions in UnitCircleWindowListener
 * are NOT only called when the mode is Free play.
 */
public class FreePlayController implements UnitCircleWindowListener, SettingsListener {

    private UnitCircleWindow window;

    public FreePlayController(UnitCircleWindow window)
    {
        this.window = window;
        window.getSettings().addSettingsListener(this);
    }

    @Override
    public void mainButtonPressed() {
        if(!window.isMode(Settings.FREE_PLAY)) return;

        window.nextQuestion();
    }

    @Override
    public void settingsChanged() {
        if(!window.isMode(Settings.FREE_PLAY)) return;

        if(window.getSettings().getCurrMode() == Settings.FREE_PLAY)
            window.getMainButton().setText("Skip");
    }

    @Override
    public void onCorrectResponse() {
        //if(!window.isMode(Settings.FREE_PLAY)) return;
    }

    @Override
    public void onIncorrectResponse() {
        //if(!window.isMode(Settings.FREE_PLAY)) return;
    }

}
