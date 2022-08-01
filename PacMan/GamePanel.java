import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;

/**
 * GamePanel has the function of making the panel for our game. GamePanel draws
 * the background for the stage. It has the function to paint. It declares the
 * walls, makes them the color blue and so on.
 *
 * @author Bedri, Richie
 * @version 2021. 5. 27.
 */
public class GamePanel
    extends JPanel
{

    /**
     * unitSize is the length of the "block".
     */
    public static double unitSize = 25.0;
    private Stage        stage;


    /**
     * Creates a new guiDisplay object.
     *
     * @param stage the stage to paint
     */
    public GamePanel(Stage stage)
    {
        this.stage = stage;
    }


    /**
     * update just repaints the stage, very basic repainting method.
     *
     * @param stage1
     *            refers to the current class' instance of stage.
     */
    public void update(Stage stage1)
    {
        this.stage = stage1;
        repaint();
    }


    /**
     * {@inheritDoc}
     */
    public void paintComponent(Graphics g)
    {
        paintBackGround(g);

        Map<NonTile, Location> map = stage.getLocManager().getMap();
        Set<NonTile> objects = map.keySet();

        for (NonTile object : objects)
        {
            int r = object.getLocation().getRow();
            int c = object.getLocation().getCol();
            BufferedImage image = object.getImage();
            g.drawImage(
                image,
                (int)(c * unitSize),
                (int)(r * unitSize),
                (int)unitSize,
                (int)unitSize,
                null);
        }

    }


    /**
     * The method paintBackGround helps us paint the background using graphics.
     *
     * @param g
     *            just a variable for the graphics.
     */
    private void paintBackGround(Graphics g)
    {
        char[][] map = stage.getMap();
        for (int r = 0; r < map.length; r++)
        {
            for (int c = 0; c < map[r].length; c++)
            {
                if (map[r][c] == Stage.WALL)
                {
                    g.setColor(Color.BLUE);
                    g.fillRect(
                        (int)(c * unitSize),
                        (int)(r * unitSize),
                        (int)unitSize,
                        (int)unitSize);
                }
                else
                {
                    g.setColor(Color.BLACK);
                    g.fillRect(
                        (int)(c * unitSize),
                        (int)(r * unitSize),
                        (int)unitSize,
                        (int)unitSize);
                }
            }
        }
    }

}
