
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Neily on 11/25/15.
 */
public class QuizTimeController implements UnitCircleWindowListener, SettingsListener {

    private static UnitCircleWindow window;

    private int timerVariable = 0;
    private int numCorrect = 0;
    private int numIncorrect = 0;
    private boolean isRunning = false;

    private Timer timer = null;

    //====================================CONSTRUCTOR
    public QuizTimeController(UnitCircleWindow window)
    {
        this.window = window;
        window.getSettings().addSettingsListener(this);
    }




    //==========================

    @Override
    public void settingsChanged() {
        if(!window.isMode(Settings.QUIZ_TIME)) return;
        System.out.println("QuizTimeController.settingsChanged()");

        window.getMainButton().setText("Start Quiz");
        window.hideQuestion();
        window.setFeedBackLabel( window.getSettings().getTimeGiven() + " seconds");

    }


    //==================ON RESPONSE
    @Override
    public void onCorrectResponse() {
        if(!window.isMode(Settings.QUIZ_TIME)) return;
        numCorrect ++;
        if(numCorrect + numIncorrect >= window.getSettings().getNumberOfQuestions())
        {
            isRunning = false;
            return;
        }
    }

    @Override
    public void onIncorrectResponse() {
        if(!window.isMode(Settings.QUIZ_TIME)) return;

        numIncorrect ++;
        if(numCorrect + numIncorrect >= window.getSettings().getNumberOfQuestions())
        {
            isRunning = false;
            return;
        }

    }

    @Override
    public void mainButtonPressed() {
        if(!window.isMode(Settings.QUIZ_TIME)) return;
        JLabel feedbackLabel = window.getFeedbackLabel();
        Settings settings = window.getSettings();
        if(!isRunning)
        {
            timerVariable = 0;
            numCorrect = 0;
            timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    timerVariable++;
                    feedbackLabel.setText(settings.getTimeGiven() - timerVariable + " seconds");
                    if (timerVariable >= settings.getTimeGiven())//|| settings.getNumberOfQuestions() <= numCorrect + numIncorrect) {
                    {
                        isRunning = false;
                    }

                    if(!isRunning)
                    {
                        timer.stop();
                        feedbackLabel.setText("FINISHED!");
                        window.getMainButton().setText("Start Quiz");
                        new QuizResults (settings.getImage(), numCorrect + " out of " + settings.getNumberOfQuestions());

                    }
                }
            });
            window.nextQuestion();
            window.getMainButton().setText("Abort Quiz");
            timer.start();
            isRunning = true;
        }else
        {
            isRunning = false;
        }
    }
}
