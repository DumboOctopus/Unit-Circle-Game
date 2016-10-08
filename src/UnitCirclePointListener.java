
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is kind of misnamed. It is attached to every button that is on the unit circle and simply checks
 * weather the user clicked the correct button when play the game. It does this by having a private point which
 * is its point on the unit circle. When it is pressed, it queries the UnitCircleWindow for the current angle the
 * user is trying to locate. If the angles are equal, (using point.equals()) then we know the user has pressed the
 * correct point on the unit circle.
 */
public class UnitCirclePointListener implements ActionListener {

    private UnitCirclePoint point;
    private static UnitCircleWindow window;

    public UnitCirclePointListener (UnitCirclePoint point)
    {
        super();
        this.point = point;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (point.equals(window.getCurrPoint())) {
            window.setFeedBackLabel("Correct!");
            window.nextQuestion();
            window.fireCorrectResponseEvent();
        } else {
            if(!window.getSettings().isCorrectToMoveOn())
            {
                window.setFeedBackLabel("Incorrect");
                window.nextQuestion();
            }else {
                window.setFeedBackLabel("Incorrect...try again");
            }
            window.fireIncorrectResponseEvent();
        }

    }

    //statics
    public static void setUnitCircleWindow (UnitCircleWindow window) {
        UnitCirclePointListener.window = window;
    }
}
