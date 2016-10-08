import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class QuizResults extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel imageLabel;
    private JLabel scoreLabel;

    private File image;

    public QuizResults(File image, String text) {
        setContentPane(contentPane);
        this.image = image;


        if(image != null) {
            ImageIcon icon = new ImageIcon(image.getAbsolutePath());
            Image tmp = icon.getImage().getScaledInstance(350, 250, Image.SCALE_DEFAULT);
            icon = new ImageIcon(tmp);
            imageLabel.setIcon(icon);
        }
        imageLabel.setText("");
        scoreLabel.setText(text);

        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        setBounds(0, 23, 400, 450);

        setVisible(true);
    }

    private void onOK() {

        dispose();
    }

    private void onCancel() {
        dispose();
    }

}
