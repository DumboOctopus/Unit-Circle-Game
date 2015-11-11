import java.awt.*;
import javax.swing.*;
import javax.swing.WindowConstants;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.Random;

/**
 * Created by Neily on 11/7/15.
 */
public class UnitCircleWindow extends JFrame {

    private int width;
    private int height;
    private double percentOfScreen;

    private Container pane = this.getContentPane();

    private UnitCirclePoint currPoint;
    private JLabel feedbackLabel;
    private JLabel currAngleLabel;

    public UnitCircleWindow ()
    {
        super("Unit Circle Game");

        width = 500;
        height = 500;
        percentOfScreen = 0.7;

        pane.setLayout(null);


        //next button:
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextQuestion();
            }
        });
        nextButton.setBounds(0,0, 40, 20);
        //System.out.println(nextButton);
        pane.add(nextButton);

        //correct or not...
        feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(width/2-100, 0, 200, 20);
        feedbackLabel.setHorizontalAlignment(JButton.CENTER);
        pane.add(feedbackLabel);

        //start up game
        currPoint = UnitCirclePoint.generateRandomPoint();

        //so all listeners can actually access the currPoint
        UnitCirclePointListener.setUnitCircleWindow(this);


        //angle display
        currAngleLabel = new JLabel(currPoint.toString(true));
        currAngleLabel.setBounds(width - 100, 0, 100, 30);
        pane.add(currAngleLabel);

        //unit circle
        int minDimension = Math.min(width, height);
        for(int i = 0; i < 360; i += 15) {
            if(i%30 == 0 || i%45==0) {
                JButton b3 = new JButton("");
                //TODO: add action listener
                b3.addActionListener(new UnitCirclePointListener(new UnitCirclePoint(360-i)));
                pane.add(b3);
                int x = (int) (percentOfScreen * minDimension * Math.cos(Math.toRadians(i)) / 2);
                int y = (int) (percentOfScreen * minDimension * Math.sin(Math.toRadians(i)) / 2);
                b3.setBounds(minDimension / 2 + x - 5, minDimension / 2 + y - 25, 10, 10);

                //System.out.println("" + x + ", " + y + ";  " + i);
            }
        }





        this.setVisible(true);
        this.setSize(width,height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setFeedBackLabel (String x)
    {
        feedbackLabel.setText(x);
    }
    public UnitCirclePoint getCurrPoint()
    {
        return currPoint;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        int minDimension = Math.min(width, height);
        for(int i = 0; i <= 360; i += 15) {
            if (i % 30 == 0 || i % 45 == 0) {
                int x = (int) (percentOfScreen * minDimension * Math.cos(Math.toRadians(i)) / 2);
                int y = (int) (percentOfScreen * minDimension * Math.sin(Math.toRadians(i)) / 2);
                Line2D lin = new Line2D.Float(minDimension / 2 + x, minDimension / 2 + y, width / 2, height / 2);
                g2.draw(lin);
            }
        }
        //g2.drawOval(width, height, (int)(percentOfScreen*minDimension), (int)(percentOfScreen* minDimension));
        drawCenteredCircle(g2, width/2, height/2, (int)(percentOfScreen*minDimension));
    }
    public void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
        x = x-(r/2);
        y = y-(r/2);
        g.drawOval(x,y,r,r);
    }

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

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UnitCircleWindow();
            }
        });
    }
}
