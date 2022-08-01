import java.util.*;
/**
 *  LocaManager class represents the manager of game objects' Locations. Other classes - Ghost, Pacman, Stage - use this
 *  class to update and refer to locations of game objects.
 *
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public class LocManager
{
    private Map<NonTile, Location>  nonTiles;
    private PacMan pacman;
    private Stage stage;
    private ArrayList<Movable> movingObjects;

    /**
     * Create a new LocManager object.
     * @param stage a stage object
     */
    public LocManager(Stage stage)
    {
        nonTiles = new HashMap<NonTile, Location>();
        this.stage = stage;
        movingObjects = new ArrayList<Movable>();
    }

    /**
     * Put(or Update) NonTile object in certain location
     * @param one NonTile object to be added in HashMap
     * @param location Location that one will move to
     */
    public void put(NonTile one, Location location)
    {
        if(stage.getFromMap(location) == stage.WALL)
        {
            return;
        }

        if(stage.getFromMap(location) == 'X')
        {
            if(stage.getFromMap(one.getLocation()) == 'r')
            {
                nonTiles.put(one, new Location(location.getRow(), 0));
            }
            else if(stage.getFromMap(one.getLocation()) == 'l')
            {
                nonTiles.put(one, new Location(location.getRow(), stage.getSize() - 1));
            }
            return;
        }


        nonTiles.put(one, location);
    }

    /**
     * return the HashMap of NonTile and Location
     * @return the HashMap of NonTile and Location
     */
    public Map<NonTile, Location> getMap()
    {
        return nonTiles;
    }

    /**
     * add the NonTile Object in the HashMap
     * @param one Nontile object to be added
     */
    public void add(NonTile one)
    {
        if(one instanceof Movable)
        {
            if(one.category().equals("PacMan"))
            {
                pacman = (PacMan)one;
            }
            movingObjects.add((Movable)one);
        }

        nonTiles.put(one, one.getLocation());
    }

    /**
     * return the location of pacman
     * @return the location of pacman
     */
    public Location getPacmanLoc()
    {
        return nonTiles.get(pacman);
    }

    /**
     * return the location of movable object
     * @param one Movable Object to be found
     * @return the location of Movable object one
     */
    public Location getMapLoc(Movable one)
    {
        return nonTiles.get(one);
    }

    /**
     * return the pacman object
     * @return pacman Pacman object
     */
    public PacMan getPacMan()
    {
        return pacman;
    }

    /**
     * remove the NonTile object from the HashMap
     * @param one NonTile object to be removed from map
     */
    public void remove(NonTile one)
    {
        nonTiles.remove(one);
    }

    /**
     * return the arraylist of movable objects
     * @return movingObjects moving objects in game
     */
    public ArrayList<Movable> getMovingObjects()
    {
        return movingObjects;
    }

}
