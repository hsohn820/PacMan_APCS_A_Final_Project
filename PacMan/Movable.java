/**
 *
 * The class moveable extends NonTile and it functions as it gives the object or
 * the entity to the right to move.
 *
 *
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public abstract class Movable extends NonTile
{
    /**
     * Creates a new Movable object.
     * @param stage a stage object
     * @param loc its location at the stage
     */
    public Movable(Stage stage, Location loc)
    {
        super(stage, loc);
    }

    /**
     * It moves to the new Location according to its situation.
     */
    public abstract void move();
}
