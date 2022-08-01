import java.awt.image.BufferedImage;

/**
 * This class NonTile represents objects that are not background.
 *
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public abstract class NonTile
{
    /**
     * UP means to go up or increase vertically.
     */
    public static final String UP = "up";

    /**
     * DOWN means to go down or decrease vertically.
     */
    public static final String DOWN = "down";

    /**
     * RIGHT means to go right or increase horizontally
     */
    public static final String RIGHT = "right";

    /**
     * LEFT means to go left or decrease horizontally
     */
    public static final String LEFT = "left";

    private Location loc;
    private Stage stage;
    private Location initLoc;
    private BufferedImage image;

    /**
     * Creates a new NonTile object.
     * @param stage the stage where this object is placed
     * @param location its location at stage
     */
    public NonTile(Stage stage, Location location)
    {
        setInitLoc(location);
        setLoc(location);
        this.stage = stage;
        stage.add(this);
    }

    /**
     * getImage is a getter for the image.
     * @return image for the graphical image
     */
    public BufferedImage getImage()
    {
        return image;
    }

    /**
     * setLocation refers to the current object (location in this case)
     * @param loc new Location to set
     */
    public void setLocation(final Location loc)
    {
        this.setLoc(loc);
    }

    /**
     * getLocation is a getter for the location
     * @return getLoc its current location
     */
    public Location getLocation()
    {
        return getLoc();
    }

    /**
     * getInitLocation gets the initial location
     * @return getInitLoc its initial location
     */
    public Location getInitLocation()
    {
        return getInitLoc();
    }

    /**
     * {@inheritDoc}
     */
    public String toString()
    {
        return category() + ": " + getLoc() + " " + getInitLoc();
    }

    /**
     * category is the abstract method here because we want to hide certain
     * details and show only the essential information to the user/player
     * @return none because its an abstract method
     */
    public abstract String category();


    /**
     * Get the current value of stage.
     * @return stage The stage object for this object.
     */
    public Stage getStage()
    {
        return stage;
    }

    /**
     * Get the current value of loc.
     * @return The value of loc for this object.
     */
    public Location getLoc()
    {
        return loc;
    }

    /**
     * Set the value of loc for this object.
     * @param loc The new value for loc.
     */
    public void setLoc(Location loc)
    {
        this.loc = loc;
    }

    /**
     * Get the current value of initLoc.
     * @return The value of initLoc for this object.
     */
    public Location getInitLoc()
    {
        return initLoc;
    }

    /**
     * Set the value of initLoc for this object.
     * @param initLoc The new value for initLoc.
     */
    public void setInitLoc(Location initLoc)
    {
        this.initLoc = initLoc;
    }

    /**
     * Set the value of image for this object.
     * @param image The new value for image.
     */
    public void setImage(BufferedImage image)
    {
        this.image = image;
    }
}
