import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 *  Stage class represent the stage of the game. It contains the 2d array of
 *  character which symbolizes game objects.
 *
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public class Stage
{
    /**
     * character symbolizes for Tile
     */
    public static final char TILE = '0';

    /**
     * character symbolizes for Wall
     */
    public static final char WALL = '#';

    /**
     * character symbolizes for Food
     */
    public static final char FOOD = 'f';

    /**
     * character symbolizes for PacMan
     */
    public static final char PM = 'c';

    /**
     * character symbolizes for Dot
     */
    public static final char DOT = 'd';

    /**
     * character symbolizes for Ghost
     */
    public static final char GT = 'g';


    private char[][] stage;
    private char[][] initMap;
    private Set<Location> emptyLocs;
    private int size;
    private LocManager locman;
    private Location leftTunnel, rightTunnel;
    private int dotNum = 0;

    /**
     * Create a new Stage object.
     */
    public Stage()
    {
        emptyLocs = new HashSet<Location>();
        locman = new LocManager(this);
        mapDraw();
        this.size = stage.length;
    }

    /**
     * In stage, this places all the NonTile Objects in right place.
     */
    public void locate()
    {
        Map<NonTile, Location> map = locman.getMap();
        Set<NonTile> things = map.keySet();
        for(NonTile one : things)
        {
            removeFromMap(one.getLocation());
        }

        for(NonTile one : things)
        {
            Location newLoc = map.get(one);
            Location oldLoc = one.getLocation();
            int row = newLoc.getRow();
            int col = newLoc.getCol();
            char symbol;
            if(one.category().equals("PacMan"))
                symbol = PM;
            else if(one.category().equals("Ghost"))
                symbol = GT;
            else if(one.category().equals("Food"))
                symbol = FOOD;
            else
                symbol = DOT;
            stage[row][col] = symbol;
            one.setLocation(newLoc);
        }
    }

    /**
     * return LocManager object
     * @return locman its LocManager object
     */
    public LocManager getLocManager()
    {
        return locman;
    }

    /**
     * It removes the object from the stage and from the LocManager
     * @param toBeRemoved remove the NonTile object from stage and locmanager
     */
    public void removeObject(NonTile toBeRemoved)
    {
        emptyLocs.add(toBeRemoved.getLocation());
        removeFromMap(toBeRemoved.getLocation());
        locman.remove(toBeRemoved);
    }

    /**
     * It deletes the symbol for an object from the stage. The location has the symbol of TILE.
     * @param loc Location where the object to be removed
     */
    private void removeFromMap(Location loc)
    {
        stage[loc.getRow()][loc.getCol()] = TILE;
    }

    /**
     * return the Random Location that is empty.
     * @return Random Location that is empty
     */
    public Location getRandLoc()
    {
        ArrayList<Location> randLocs = new ArrayList<Location>(emptyLocs);
        return randLocs.get((int)(Math.random() * randLocs.size()));
    }

    /**
     * Read the file and draw the initial map.
     */
    private void mapDraw()
    {
        try
        {
            File charMap = new File("charMap");
            Scanner scan = new Scanner(charMap);
            int size = scan.nextInt();
            stage = new char[size][size];
            initMap = new char[size][size];
            scan.nextLine();
            for(int i = 0; i < size; i++)
            {
                String line = scan.nextLine();
                for(int j = 0; j < line.length(); j++)
                {
                    char a = line.charAt(j);
                    stage[i][j] = a;
                    initMap[i][j] = a;
                    if(a == 'f')
                    {
                        new Food(this, new Location(i, j));
                    }
                    else if(a == 'd')
                    {
                        new Dot(this, new Location(i, j));
                        dotNum++;
                    }
                    else if(a == 'p')
                    {
                        new PowerPellet(this, new Location(i, j));
                    }
                }
            }
            scan.close();

        } catch(FileNotFoundException e) {
            System.out.println("FAILEDDDDDDDDDDDDDDDDDDDDDD");
        }

    }

    /**
     * Add the NonTile Object in LocManager
     * @param one NonTile Object to be added
     */
    public void add(NonTile one)
    {
        locman.add(one);
    }

    /**
     * return the location of PacMan
     * @return pacman's location
     */
    public Location getPacManLoc()
    {
        return locman.getPacmanLoc();
    }

    /**
     * return symbol of the object in the Location
     * @param loc Location to get from
     * @return character of object in the location
     */
    public char getFromMap(Location loc)
    {
        if(loc.getRow() > size - 1 || loc.getRow() < 0 || loc.getCol() > size - 1 || loc.getCol() < 0)
        {
            return 'X';
        }
        return initMap[loc.getRow()][loc.getCol()];
    }


    /**
     * {@inheritDoc}
     */
    public String toString()
    {
        String result = "";
        for(int r = 0; r < stage.length; r++)
        {
            for(int c = 0; c < stage[r].length; c++)
            {
                result += stage[r][c];
            }
            result += "\n";
        }

        return result;
    }

    /**
     * return the 2d array of stage
     * @return stage its current stage
     */
    public char[][] getMap()
    {
        return stage;
    }

    /**
     * return the size of stage
     * @return size the size of stage
     */
    public int getSize()
    {
        return size;
    }

    /**
     * check if PacMan eats any eatable objects and gets caught by a ghost.
     *
     * @return the array of boolean; first check if pacman touch ghost, second food, third dot, and forth powerpellet
     */
    public boolean[] checkGameState()
    {
        Map<NonTile, Location> mapLocations = locman.getMap();
        Set<NonTile> objects = mapLocations.keySet();
        NonTile toBeRemoved = null;
        boolean eatFood = false;
        boolean eatDot = false;
        boolean caught = false;
        boolean eatPower = false;
        Location newPacLoc = getPacManLoc();
        Location oldPacLoc = locman.getPacMan().getLocation();
        for(NonTile object : objects)
        {
            if(object.category().equals("Food") && object.getLocation().equals(newPacLoc))
            {
                toBeRemoved = object;
                eatFood = true;
            }
            else if(object.category().equals("Dot") && object.getLocation().equals(newPacLoc))
            {
                toBeRemoved = object;
                eatDot = true;
                dotNum--;
            }
            else if(object.category().equals("PowerPellet") && object.getLocation().equals(newPacLoc))
            {
                toBeRemoved = object;
                eatPower = true;
            }
            else if(object.category().equals("Ghost"))
            {
                if(object.getLocation().equals(newPacLoc) && locman.getMapLoc((Movable)object).equals(oldPacLoc) || locman.getMapLoc((Movable)object).equals(newPacLoc))
                {
                    caught = true;
                    break;
                }
            }
        }

        if(eatFood || eatDot || eatPower)
        {
            ((Eatable)toBeRemoved).hasEaten();
            removeObject(toBeRemoved);
        }

        boolean[] result = {caught, eatFood, eatDot, eatPower};

        return result;
    }

    /**
     * Check if the direction is open to move.
     * @param loc Location to check
     * @param dir Direction to move
     * @return true if the direction is open to move, false otherwise
     */
    public boolean isOpen(Location loc, String dir)
    {
        char inFront;
        switch(dir)
        {
            case "left":
                inFront = getFromMap(new Location(loc.getRow(), loc.getCol() - 1));
                break;
            case "up":
                inFront = getFromMap(new Location(loc.getRow() - 1, loc.getCol()));
                break;
            case "right":
                inFront = getFromMap(new Location(loc.getRow(), loc.getCol() + 1));
                break;
            case "down":
                inFront = getFromMap(new Location(loc.getRow() + 1, loc.getCol()));
                break;
            default:
                return false;
        }

        return inFront != WALL && inFront != 'X'; //if it is wall or outOfBound
    }

    /**
     * put every moving objects to initial position
     */
    public void resetObjects()
    {
        for(Movable one : locman.getMovingObjects())
        {
            locman.put(one, one.getInitLocation());
        }
    }

    /**
     * return the number of dots
     * @return dotNum the number of dots
     */
    public int numOfDot()
    {
        return dotNum;
    }

}
