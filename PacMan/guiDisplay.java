import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This is the main file that we run the game in. It is also the basic main menu
 * for our game. There are 3 buttons, the start button, the tutorial button and
 * the skip button. You enter your user name and press start to play the game.
 * You can skip and not enter a user name and continue playing. You can also see
 * a 2 sentence tutorial with the tutorial button.
 *
 * @author Bedri, Richie
 * @version 2021. 5. 27.
 */
public class guiDisplay
    implements ActionListener
{
    /**
     * this button is the start button.
     */
    JButton    buttonStart;
    /**
     * skip button that lets you skip the username part.
     */
    JButton    buttonSkip;
    /**
     * this button gives you a 2 sentenced tutorial.
     */
    JButton    buttonTutorial;
    /**
     * "f" is just a shortcut for the JFrame call.
     */
    JFrame     f;
    /**
     * this "gf" stands for gameFrame and lets us access GameFrame when needed.
     */
    GameFrame  gf;
    /**
     * just a shortcut for the Tutorial call.
     */
    Tutorial   t;
    /**
     * p is the Panel that holds everything.
     */
    JPanel     p        = new JPanel();
    /**
     * l is the JLabel for the user to "enter" their name.
     */
    JLabel     l        = new JLabel("Enter Name");
    /**
     * userText is the textField for the user to enter their name.
     */
    JTextField userText = new JTextField(20);


    /**
     * Just a main method that starts our gui.
     *
     * @param args
     *            takes arguments
     */
    public static void main(String[] args)
    {
        guiDisplay gui = new guiDisplay();
        gui.start();
    }


    /**
     * This is our start method, this is where we have everything laid out.
     */
    public void start()
    {

        f = new JFrame("PacMan");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400, 300);
        f.setLocation(100, 100);
        final JTextArea textArea = new JTextArea(10, 40);
        final JTextArea textArea1 = new JTextArea(5, 10);
        f.getContentPane().add(BorderLayout.CENTER, textArea);
        f.getContentPane().add(BorderLayout.CENTER, textArea1);
        buttonTutorial = new JButton("How to play");
        buttonStart = new JButton("Start");

        buttonSkip = new JButton("(Skip for now)");
        buttonSkip.addActionListener(this);
        buttonTutorial.addActionListener((this));
        buttonTutorial.setBounds(10, 150, 100, 30);
        f.getContentPane().add(buttonTutorial);
        f.getContentPane().add(BorderLayout.SOUTH, buttonStart);
        f.getContentPane().add(BorderLayout.NORTH, buttonSkip);

        f.add(p);
        p.setLayout(null);
        l.setBounds(10, 20, 80, 25);
        p.add(l);

        userText.setBounds(100, 20, 165, 25);
        p.add(userText);

        buttonStart.addActionListener(this);

        f.setVisible(true);

    }


    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e)
    {
        String text;
        if (e.getSource().equals(buttonStart))
        {
            if (userText.getText().isEmpty())
            {
                System.out.println("Incorrect username!");
                JOptionPane.showMessageDialog(null, "Enter a valid username");
                return;

            }

            f.dispose();
            text = userText.getText();
            gf = new GameFrame("PacMan", text);
        }
        else if (e.getSource().equals(buttonSkip))
        {
            f.dispose();
            text = "Guest";
            gf = new GameFrame("PacMan", text);
        }
        else if (e.getSource().equals(buttonTutorial))
        {
            t = new Tutorial();
        }

    }

}
