import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The PowerPellet class is another entity that can be consumed by the player
 * in order to eat/kill the ghosts. The power pellet has the same function
 * as the food however they're placed on the corners of the stage/map.
 *
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public class PowerPellet extends Eatable
{

    /**
     * Creates a new PowerPellet object.
     * @param stage is the stage for our power pellet to be displayed on
     * @param location is the location that the power pellet gets put on
     */
    public PowerPellet(Stage stage, Location location)
    {
        super(stage, location);
        isEaten = false;
        stage.add(this);
        try
        {
            setImage(ImageIO.read(new File("powerpallet.png")));
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
        return "PowerPellet";
    }

}

