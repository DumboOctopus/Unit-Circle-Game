
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Neily on 11/7/15.
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

        if(point.equals(window.getCurrPoint()))
        {
            window.setFeedBackLabel("Correct!");
            window.nextQuestion();
        } else
        {
            window.setFeedBackLabel("Incorrect...try again");
        }
    }

    //statics
    public static void setUnitCircleWindow (UnitCircleWindow window) {
        UnitCirclePointListener.window = window;
    }
}
