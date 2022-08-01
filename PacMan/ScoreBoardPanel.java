import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * ScoreBoardPanel class helps to display life, current score, and best score.
 *
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public class ScoreBoardPanel extends JPanel
{
    private int unitSize = 25;
    private BufferedImage lifeImage;
    private int life;
    /**
     * Creates a new guiDisplay object.
     * @param life pacman's life
     */
    public ScoreBoardPanel(int life)
    {
        this.life = life;
        setBackground(Color.BLACK);
        try
        {
            lifeImage = ImageIO.read(new File("life.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * the update method updates life and repaints it accordingly
     * @param life the amount of life a player has
     */
    public void update(int life)
    {
        this.life = life;
        repaint();
    }


    /**
     * {@inheritDoc}
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for(int i = 0 ; i < life; i++)
        {
            g.drawImage(lifeImage, (int)(i * unitSize), (int)(20), (int)unitSize, (int)unitSize, null);
        }
    }

}
