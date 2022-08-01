import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 *  The food class takes cares of the multiple food images.
 *
 *
 *  We imported images of a cherry, snack and a strawberry that the player can
 *  eat. That is also why this Food class extends Eatable.
 *
 *
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public class Food extends Eatable
{

    /**
     * Creates a new Food object.
     * @param stage a stage object to locate this food
     *
     * @param loc its location in stage
     */
    public Food(Stage stage, Location loc)
    {
        super(stage, loc);
        isEaten = false;
        stage.add(this);
        try
        {
            if((int)(Math.random() * 6) <= 1)
            {
                setImage(ImageIO.read(new File("cherry.png")));
            }
            else if((int)(Math.random() * 6) <= 3)
            {
                setImage(ImageIO.read(new File("snack.png")));
            }
            else
            {
                setImage(ImageIO.read(new File("strawberry.png")));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String category()
    {
        return "Food";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hasEaten()
    {
        isEaten = true;
    }
}
