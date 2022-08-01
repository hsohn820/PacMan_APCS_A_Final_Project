import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The ghost class adds multiple images of the ghosts into the game.
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public class Ghost extends Movable
{
    private String direction;
    private BufferedImage normalImage;
    private boolean blueMode = false;

    /**
     * Creates a new Ghost object.
     * @param stage a stage object to locate this ghost
     * @param loc its location at stage
     */
    public Ghost(Stage stage, Location loc)
    {
        super(stage, loc);
        direction = UP;
        try
        {
            if((int)(Math.random() * 6) <= 1)
            {
                setImage(ImageIO.read(new File("blinky.png")));
            }
            else if((int)(Math.random() * 6) <= 3)
            {
                setImage(ImageIO.read(new File("orange.png")));
            }
            else
            {
                setImage(ImageIO.read(new File("pinky.png")));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        normalImage = getImage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String category()
    {
        return "Ghost";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move()
    {
        if(blueMode || (int)(Math.random() * 4) == 3)
        {
            moveStraight();
            return;
        }

        String checkSide;
        Location pacmanLoc = getStage().getPacManLoc();
        int pRow = pacmanLoc.getRow();
        int pCol = pacmanLoc.getCol();
        int row = getLoc().getRow();
        int col = getLoc().getCol();
        if(direction.equals(UP) || direction .equals(DOWN))
        {
            if(pCol < col)
            {
                checkSide = LEFT;
            }
            else if(pCol == col)
            {
                checkSide = direction;
            }
            else
            {
                checkSide = RIGHT;
            }
        }
        else
        {
            if(pRow < row)
            {
                checkSide = UP;
            }
            else if(pRow == row)
            {
                checkSide = direction;
            }
            else
            {
                checkSide = DOWN;
            }
        }


        if(getStage().isOpen(getLoc(), checkSide))
        {
            direction = checkSide;
        }
        else if(!getStage().isOpen(getLoc(), direction))
        {
            if(getStage().getFromMap(getLoc()) == 'r' && direction.equals("right")
                || getStage().getFromMap(getLoc()) == 'l' && direction.equals("left"))
            {
                moveInDirection(direction);
                return;
            }
            reverseDirection();
        }

        moveInDirection(direction);
    }

    private void moveStraight()
    {
        if(!getStage().isOpen(getLoc(), direction))
        {
            if(getStage().getFromMap(getLoc()) == 'r' && direction.equals("right")
                || getStage().getFromMap(getLoc()) == 'l' && direction.equals("left"))
            {
                moveInDirection(direction);
                return;
            }
            reverseDirection();
        }
        moveInDirection(direction);
    }

    private void moveInDirection(String dir)
    {
        LocManager locman = getStage().getLocManager();
        if(dir.equals("up"))
            locman.put(this, new Location(getLoc().getRow() - 1, getLoc().getCol()));
        else if(dir.equals("down"))
            locman.put(this, new Location(getLoc().getRow() + 1, getLoc().getCol()));
        else if(dir.equals("right"))
            locman.put(this, new Location(getLoc().getRow(), getLoc().getCol() + 1));
        else if(dir.equals("left"))
            locman.put(this, new Location(getLoc().getRow(), getLoc().getCol() - 1));
    }

    private void reverseDirection()
    {
        if(direction.equals(UP))
        {
            direction = DOWN;
        }
        else if(direction.equals(DOWN))
        {
            direction = UP;
        }
        else if(direction.equals(RIGHT))
        {
            direction = LEFT;
        }
        else
        {
            direction = RIGHT;
        }
    }

    /**The method blueMode makes the ghosts blue when a snack or a power pellet
     * is eaten. blueMode makes it so the ghosts are edible.
     */
    public void blueMode()
    {
        blueMode = true;
        try
        {
            setImage(ImageIO.read(new File("BlueModeGhost.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * normalMode is basically the opposite version of the BlueMode. You cant
     * eat
     */
    public void normalMode()
    {
        blueMode  = false;
        setImage(normalImage);
    }

    /**
     * gotEaten puts the game back or "resets" the round because pac-man has
     * been eaten
     */
    public void gotEaten()
    {
        LocManager locman = getStage().getLocManager();
        normalMode();
        locman.put(this, getInitLoc());
    }

    /**
     * isBlueMode simply checks if the blueMode is true or not.
     * @return blueMode
     */
    public boolean isBlueMode()
    {
        return blueMode;
    }
}
