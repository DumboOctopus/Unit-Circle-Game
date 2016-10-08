import java.awt.*;
import javax.swing.*;
import javax.swing.WindowConstants;


import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Neily on 11/7/15.
 */
public class UnitCircleWindow extends JApplet {
    //TODO: see all other files
    //TODO: document all files
    //TODO: Have a UnitCircle extends JComponent :)
    //TODO: THink: this class encapulsates angle and unit circle, it just lets other decide what happens whith right and wrong.

//    public static final int QUIZ_TIME = 1, FREE_PLAY = 0;
//    private int currMode;

    private Settings settings;
    private ArrayList<UnitCircleWindowListener> listeners;

    private int width;
    private int height;
    private double percentOfScreen;

    private Container pane = this.getContentPane();

    private UnitCirclePoint currPoint;
    private JLabel feedbackLabel;
    private JLabel currAngleLabel;
    private JButton mainButton;

//    private int numCorrect;
//    private int timerVariable;

    //file :)
    //private final JFileChooser fc = new JFileChooser();
    //private File image;

    private QuizTimeController quizTimeController;
    private FreePlayController freePlayController;
    private SettingsController settingsController;

    //menu bar
    private JMenuBar menuBar;

    //TODO look around at the classes before starting
    public UnitCircleWindow ()
    {

        width = 500;
        height = 500;
        percentOfScreen = 0.7;
        settings = new Settings(this);

        pane.setLayout(null);

        //start up game
        currPoint = UnitCirclePoint.generateRandomPoint();

        //so all listeners can actually access the currPoint
        UnitCirclePointListener.setUnitCircleWindow(this);

        //init components so controllers can set them up;
        setUpLabels();
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        mainButton = new JButton("Skip");
        mainButton.setBounds(0, 0, 100, 30);
        mainButton.addActionListener(e -> fireMainButtonPressedEvent());
        pane.add(mainButton);


        //controllers
        quizTimeController = new QuizTimeController(this);
        freePlayController = new FreePlayController(this);
        settingsController = new SettingsController(this);

        //registering them as listeners to UnitCircle window events
        listeners = new ArrayList<>();
        listeners.add(quizTimeController);
        listeners.add(freePlayController);

        //UnitCircleWindowEvent event = new UnitCircleWindowEvent(this);
        settingsController.setUpJMenu();
//        quizTimeController.onSetUp(); //event as arg
//        freePlayController.onSetUp(); //event as arg



//        setUpSkipButton();
//        setUpMenuOptions();
//        setUpLabels();
//        setUpUnitCircleButtons();

        //init vars
//        numberOfQuestions = 0;
//        timeGiven = 0;

        //init unit circle
        setUpUnitCircleButtons();

        this.setVisible(true);
        this.setSize(width,height);

    }

    public void setUpLabels()
    {
        //angle display
        currAngleLabel = new JLabel(currPoint.toString(true));
        currAngleLabel.setBounds(width - 100, 0, 100, 30);
        pane.add(currAngleLabel);

        //correct or not...
        feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(width/2-100, 0, 200, 20);
        feedbackLabel.setHorizontalAlignment(JButton.CENTER);
        pane.add(feedbackLabel);
    }
    //helpers for constructor
    public void setUpUnitCircleButtons()
    {
        //unit circle

        //System.out.println(menuBar);

        int minDimension = Math.min(width, height);
        for(int i = 0; i < 360; i += 15) {
            if(i%30 == 0 || i%45==0) {
                JButton b3 = new JButton("");

                b3.addActionListener(new UnitCirclePointListener(new UnitCirclePoint(360-i)));
                pane.add(b3);
                int x = (int) (1.05*percentOfScreen * minDimension * Math.cos(Math.toRadians(i)) / 2);
                int y = (int) (1.05*percentOfScreen * minDimension * Math.sin(Math.toRadians(i)) / 2) - (int)menuBar.getPreferredSize().getHeight();
                b3.setBounds(minDimension / 2 + x - 5, minDimension / 2 + y - 25, 10, 10);

                //System.out.println("" + x + ", " + y + ";  " + i);
            }
        }
    }

    //========================setters and getters
    public void setFeedBackLabel (String x)
    {
        feedbackLabel.setText(x);
    }

    public UnitCirclePoint getCurrPoint()
    {
        return currPoint;
    }
    public Settings getSettings() {
        return settings;
    }

    @Deprecated
    public JLabel getFeedbackLabel() {
        return feedbackLabel;
    }
    public JButton getMainButton() {

        return mainButton;
    }
    public boolean isMode(int mode){
        return(mode == settings.getCurrMode());
    }

    public JMenuBar getJMenuBar() {
        return menuBar;
    }

    //=======================overrides
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        int minDimension = Math.min(width, height);

        //draws cicle

        g.setColor(new Color(255, 231, 209));
        drawCenteredCircle(g2, width / 2, height / 2, (int) (percentOfScreen * minDimension));

        g.setColor(Color.BLACK);
        for(int i = 0; i <= 360; i += 15) {
            if (i % 30 == 0 || i % 45 == 0) {
                int x = (int) (percentOfScreen * minDimension * Math.cos(Math.toRadians(i)) / 2);
                int y = (int) (percentOfScreen * minDimension * Math.sin(Math.toRadians(i)) / 2) ;
                Line2D lin = new Line2D.Float((minDimension )/ 2 + x, minDimension / 2 + y, width / 2, height / 2);
                g2.draw(lin);
            }
        }

    }
    public void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
        x = x-(r/2);
        y = y-(r/2);

        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, r, r);
        g.fill(circle);
        g.setColor(Color.BLACK);
        g.drawOval(x,y,r,r);
    }


    //======================OTHER METHODS
    public void nextQuestion()
    {
        UnitCirclePoint newPoint = UnitCirclePoint.generateRandomPoint();
        while(newPoint.equals(currPoint))
        {
            newPoint = UnitCirclePoint.generateRandomPoint();
        }
        currPoint = newPoint;
        Random myRand = new Random();
        currAngleLabel.setText(currPoint.toString(myRand.nextBoolean()));
    }
    public void hideQuestion()
    {
        currAngleLabel.setText("");
    }
    public void promptSettingsForQuizTime()
    {
        //Object[] possibilities = {"ham", "spam", "yam"};
        if(settings.getTimeGiven() <= 0) promptTimeGivenForQuiz();
        if(settings.getNumberOfQuestions() <= 0) promptNumberOfQuestionsForQuiz();
    }
    public void promptTimeGivenForQuiz ()
    {
        settings.setTimeGiven((int) promptDouble(
                "Time",
                "How much time (in seconds): ",
                settings.getTimeGiven())
        );
    }


    public void promptNumberOfQuestionsForQuiz ()
    {
        settings.setNumberOfQuestions((int)promptDouble(
                "Number Of dem questions",
                "How many questions (in number of course): ",
                settings.getNumberOfQuestions())
        );
    }

    //================PROMPTS
    public double promptDouble (String title, String message)
    {
        double tmp = 0;
        String s = (String) JOptionPane.showInputDialog(
                this,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                30);
        try {
            tmp = (Integer.parseInt(s));

        } catch(NumberFormatException e)
        {
            return promptDouble(title, message); //maybe user might troll...
        }
        if(tmp <= 0)
        {
            return promptDouble(title, message);
        }
        return tmp;
    }
    public double promptDouble (String title, String message, int suggestion)
    {
        double tmp = 0;
        String s = (String) JOptionPane.showInputDialog(
                this,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                suggestion);
        try {
            tmp = (Integer.parseInt(s));

        } catch(NumberFormatException e)
        {
            return promptDouble(title, message); //maybe user might troll...
        }
        if(tmp <= 0)
        {
            return promptDouble(title, message);
        }
        return tmp;
    }


    //===============MANAGING THEM LISTENERS (THOSE CREEPERS bleh)
    //TODO: replace foreachs cuz effiecency
    public void fireCorrectResponseEvent()
    {
        //UnitCircleWindowEvent event = new UnitCircleWindowEvent(this);
        for(UnitCircleWindowListener l: listeners)
        {
            //l.onCorrectResponse(event);
            l.onCorrectResponse();
        }
    }

    public void fireIncorrectResponseEvent()
    {
        //UnitCircleWindowEvent event = new UnitCircleWindowEvent(this);
        for(UnitCircleWindowListener l: listeners)
        {
            //l.onIncorrectResponse(event);
            l.onIncorrectResponse();
        }
    }
    public void fireMainButtonPressedEvent()
    {
        //UnitCircleWindowEvent event = new UnitCircleWindowEvent(this);
        for(UnitCircleWindowListener l: listeners)
        {
            //l.mainButtonPressed(event);
            l.mainButtonPressed();
        }
    }
    //the last on is managed by the main button itself (the main button pressed

    @Override
    public void init() {
        try {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new UnitCircleWindow();
                }
            });
        }catch (Exception e){
            System.err.println("createGUI didn't complete successfully");
        }
    }
}
