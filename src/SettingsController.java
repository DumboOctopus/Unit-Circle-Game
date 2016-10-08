import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Manage the JMenu. This mutates the settings and runs some set up stuff that is not requiring access to privates
 * in the other controller classes.
 * TODO: everything in the class: see QuizTimeController.setUpMenu for code and info
 */
public class SettingsController  {
    //TODO: all controller classes have static UnitCircleWindow. Can inheirtance clean up stuff?
    private static UnitCircleWindow window;
    private final JFileChooser fc = new JFileChooser();

    //menu
    JMenu submenu;
    JRadioButtonMenuItem radioButton;
    JMenuItem menuItem;

    ///
    Settings settings;

    public SettingsController(UnitCircleWindow window)
    {
        this.window = window;
        settings = window.getSettings();
    }

    //TODO: split dis up
    //TODO: this is only run at initialization, just have this in the constructor #effeciency
    public void setUpJMenu()
    {
        setUpGeneralMenu();
        setUpQuizTimeMenu();
    }

    private void setUpGeneralMenu(){
        submenu = new JMenu("General");
        radioButton = new JRadioButtonMenuItem("Correct Answer necessary get next question");
        radioButton.setToolTipText("You are not allowed to move on in the quiz until you answer the question correctly");
        radioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setCorrectToMoveOn(!settings.isCorrectToMoveOn());
            }
        });
        radioButton.setSelected(window.getSettings().isCorrectToMoveOn());
        submenu.add(radioButton);
        window.getJMenuBar().add(submenu);
    }


    private void setUpQuizTimeMenu()
    {

        submenu = new JMenu("Timed Quiz");


        radioButton = new JRadioButtonMenuItem("Activate");
        radioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!window.isMode(Settings.QUIZ_TIME)) {
                    settings.setCurrMode(Settings.QUIZ_TIME);
                } else {
                    settings.setCurrMode(Settings.QUIZ_TIME);
                }
                if(settings.getNumberOfQuestions() <= 0)
                {
                    settings.setNumberOfQuestions((int) window.promptDouble(
                                    "Number Of dem questions",
                                    "How many questions (in number of course): ",
                                    settings.getNumberOfQuestions())
                    );
                }
                if(settings.getTimeGiven() <= 0)
                {
                    settings.setTimeGiven((int)window.promptDouble(
                                    "Time",
                                    "How much time (in seconds): ",
                                    settings.getTimeGiven())
                    );

                }
            }
        });
        submenu.add(radioButton);




        menuItem = new JMenuItem("Seconds");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setTimeGiven((int) window.promptDouble(
                                "Time",
                                "How much time (in seconds): ",
                                settings.getTimeGiven())
                );
                //TODO have a way to keep track of number of qu ask
            }
        });
        submenu.add(menuItem);

        menuItem = new JMenuItem("Number Of Problems");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setNumberOfQuestions((int) window.promptDouble(
                                "Number Of dem questions",
                                "How many questions (in number of course): ",
                                settings.getNumberOfQuestions())
                );
                //TODO: so numberOfQuestions is shown in label
            }
        });
        submenu.add(menuItem);

        menuItem = new JMenuItem("Picture Of Teacher");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: only images allowed
                int returnVal = fc.showOpenDialog(window);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    settings.setImage(fc.getSelectedFile());

                } else {
                    //TODO
                }
            }
        });
        submenu.add(menuItem);

        window.getJMenuBar().add(submenu);
    }


}
