import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 *  This is the dot class, it extends eatable, just a simple class for the
 *  image.
 *
 *  This class is the image for the dot.
 *
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public class Dot extends Eatable
{
    /**
     * Creates a new Dot object with stage and location.
     * @param stage a stage object to locate this dot
     * @param loc its location at stage
     */
    public Dot(Stage stage, Location loc)
    {
        super(stage, loc);
        isEaten = false;
        try
        {
            setImage(ImageIO.read(new File("dot.png")));
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
        return "Dot";
    }

}
