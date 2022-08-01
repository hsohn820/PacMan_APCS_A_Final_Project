import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *  Here is the PacMan class that holds information about Pacman.
 *  There are controls on how to move yourself/the pacman and also images of
 *  every rotation of the pacman, for example pacman facing upwards, downwards
 *  and so on.
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public class PacMan extends Movable
{
    private String direction;
    private BufferedImage image_UP, image_DOWN, image_RIGHT, image_LEFT;

    /**
     * Creates a new PacMan object.
     * @param stage is to display the pacman on stage
     * @param location is to display pacmans location.
     */
    public PacMan(Stage stage, Location location)
    {
        super(stage, location);
        direction = DOWN;
        try
        {
            image_UP = ImageIO.read(new File("pacmanUp.png"));
            image_DOWN = ImageIO.read(new File("pacmanDown.png"));
            image_RIGHT = ImageIO.read(new File("pacmanRight.png"));
            image_LEFT = ImageIO.read(new File("pacmanLeft.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void move()
    {
        LocManager locman = getStage().getLocManager();
        if(direction.equals(UP))
        {
            locman.put(this, new Location(getLoc().getRow() - 1, getLoc().getCol()));
        }
        else if(direction.equals(DOWN))
        {
            locman.put(this, new Location(getLoc().getRow() + 1, getLoc().getCol()));
        }
        else if(direction.equals(RIGHT))
        {
            locman.put(this, new Location(getLoc().getRow(), getLoc().getCol() + 1));
        }
        else
        {
            locman.put(this, new Location(getLoc().getRow(), getLoc().getCol() - 1));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Location getLocation()
    {
        return getLoc();
    }

    /**
     * this is just a setter for the direction.
     * @return its current direction
     */
    public String getDirection()
    {
        return direction;
    }

    /**
     * This is also another setter for the direction.
     * @param d String that would set direction to
     */
    public void setDirection(String d)
    {
        direction = d;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLocation(Location loc)
    {
        this.setLoc(loc);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String category()
    {
        return "PacMan";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getImage()
    {
        if(direction.equals(UP))
        {
            return image_UP;
        }
        else if(direction.equals(DOWN))
        {
            return image_DOWN;
        }
        else if(direction.equals(RIGHT))
        {
            return image_RIGHT;
        }
        else
        {
            return image_LEFT;
        }
    }

}
