/**
 * This is the simple Eatable class.
 *
 * This class helps whether the specified object is eaten or not.
 *
 *
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public abstract class Eatable extends NonTile
{
    /**
     * Creates a new Eatable object.
     * @param stage a stage object
     * @param location its location at stage
     */
    public Eatable(Stage stage, Location location)
    {
        super(stage, location);
    }

    /**
     *  isEaten checks if its eaten
     */
    protected boolean isEaten;

    /**
     * hasEaten checks if its eaten
     */
    public void hasEaten()
    {
        isEaten = true;
    }
}
