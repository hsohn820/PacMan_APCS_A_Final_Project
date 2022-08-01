import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * Tutorial class is here to explain the user how the game works. When its
 * clicked via the main menu (gui display) it opens a tutorial gui that
 *
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */

public class Tutorial extends JFrame
{

    // JPanel a = new JPanel();
    // JLabel ab = new JLabel("To play the game you need to use W,A,S,D to move around. The more white dots you consume, the more your score");
    // JLabel ab1 = new JLabel("incrases. Eat the orange/yellow dots to kill ghosts and ");
    // JFrame  test;
    // JButton abc;

    private JPanel panel = new JPanel();
    private JLabel TextLabel = new JLabel("To play the game you need to use W,A,S,D to move around. White dot = Score. Food = Score. PowerPellet = Eat Ghost");

    /**
     * Creates a new Tutorial object.
     */
    public Tutorial()
    {
        panel.setLayout(new BorderLayout());
        setLocation(500, 300);
        setResizable(false);
        TextLabel.setBounds(600,200,80,25);
        setVisible(true);
        panel.add(TextLabel, BorderLayout.NORTH);
        panel.add(new JLabel(new ImageIcon("tutorialPacMan.png")), BorderLayout.SOUTH);
        add(panel);
        pack();
    }

}

