import java.util.*;

/**
 * PacManGame class represents the overall game. It contains the logic of the
 * game, checking and controling game state, score, and life.
 *
 * @author Bedri, Richie
 * @version 2021. 5. 27.
 */
public class PacManGame
{
    private Stage            stage;
    private PacMan           pacman;
    private List<Ghost>      ghosts;
    private int              life;
    private int              score;
    private int              foodAdd;
    private boolean          canEatGhost;
    private final static int powerDuration = 30;
    private final static int ghostScore    = 100;
    private final static int foodScore     = 50;
    private int              powerRemain;


    /**
     * Create a new PacManGame object.
     *
     * @param stage
     *            Stage object
     */
    public PacManGame(Stage stage)
    {
        this.stage = stage;
        pacman = new PacMan(stage, new Location(1, 1));
        ghosts = new ArrayList<Ghost>();
        ghosts.add(new Ghost(stage, new Location(9, 8)));
        ghosts.add(new Ghost(stage, new Location(10, 9)));
        ghosts.add(new Ghost(stage, new Location(11, 9)));

        foodAdd = 0;
        life = 3;
        score = 0;
        setCanEatGhost(false);
    }


    /**
     * update the locations of Pacman and Ghosts
     *
     * @param dir
     *            direction of Pacman's movement
     * @return true if Pacman gets caught, false otherwise
     */
    public boolean stateUpdate(String dir)
    {
        if (stage.isOpen(pacman.getLocation(), dir))
        {
            pacman.setDirection(dir);
        }

        addFood();

        ArrayList<Movable> movingObjects = stage.getLocManager().getMovingObjects();

        for (Movable object : movingObjects)
        {
            object.move();
        }

        boolean[] state = stage.checkGameState();

        if (state[1]) // if pacman eat food
        {
            score += foodScore;
        }
        else if (state[2]) // if pacman eat dot
        {
            score++;
        }
        else if (state[3])
        {
            setCanEatGhost(true);
            powerRemain = powerDuration;
            for (Ghost ghost : ghosts)
            {
                ghost.blueMode();
            }
        }

        if (isCanEatGhost())
        {
            if (powerRemain > 0)
            {
                powerRemain--;
            }
            else
            {
                setCanEatGhost(false);
                for (Ghost ghost : ghosts)
                {
                    ghost.normalMode();
                }
            }
        }
        else
        {
            for (Ghost ghost : ghosts)
            {
                ghost.normalMode();
            }
        }

        if (state[0])
        {
            if (isCanEatGhost())
            {
                LocManager locman = stage.getLocManager();
                Location newPacLoc = stage.getPacManLoc();
                Location oldPacLoc = locman.getPacMan().getLocation();
                int i;
                for (i = 0; i < ghosts.size(); i++)
                {
                    Ghost ghost = ghosts.get(i);
                    if (ghost.getLocation().equals(newPacLoc)
                        && locman.getMapLoc(ghost).equals(oldPacLoc)
                        || locman.getMapLoc(ghost).equals(newPacLoc))

                    {
                        if (!ghost.isBlueMode()) // is this ghost revived one
                        // that is not bluemode
                        {
                            life--;
                            return true;
                        }
                        ghost.gotEaten();
                        score += ghostScore;
                    }
                }
            }
            else
            {
                life--;
                return true;
            }

        }

        return false;
    }


    /**
     * set all ghosts to normal mode and places all moving objects to initial
     * position
     */
    public void resetObjects()
    {
        setCanEatGhost(false);
        stage.resetObjects();
    }


    /**
     * return the life of pacman
     *
     * @return life life of pacman
     */
    public int getLife()
    {
        return life;
    }


    /**
     * return the score of the player
     *
     * @return score score of the player
     */
    public int getScore()
    {
        return score;
    }


    /**
     * put Food in Random position
     */
    private void putFoodInRandom()
    {
        Location randLoc = stage.getRandLoc();
        new Food(stage, randLoc);
        foodAdd++;
    }


    /**
     * add Food if it has right condition
     */
    private void addFood()
    {
        if (foodAdd <= 0 && stage.numOfDot() <= 120)
        {
            putFoodInRandom();
        }
        else if (foodAdd <= 1 && stage.numOfDot() <= 100)
        {
            putFoodInRandom();
        }
        else if (foodAdd <= 2 && stage.numOfDot() <= 80)
        {
            putFoodInRandom();
        }
        else if (foodAdd <= 3 && stage.numOfDot() <= 60)
        {
            putFoodInRandom();
        }
        else if (foodAdd <= 4 && stage.numOfDot() <= 40)
        {
            putFoodInRandom();
        }
        else if (foodAdd <= 5 && stage.numOfDot() <= 20)
        {
            putFoodInRandom();
        }
    }


    /**
     * return boolean if there is no more dots
     *
     * @return true if no more dots, false otherwise
     */
    public boolean ateAll()
    {
        return stage.numOfDot() == 0;
    }



    /**
     * Get the current value of canEatGhost.
     *
     * @return The value of canEatGhost for this object.
     */
    public boolean isCanEatGhost()
    {
        return canEatGhost;
    }


    /**
     * Set the value of canEatGhost for this object.
     *
     * @param canEatGhost
     *            The new value for canEatGhost.
     */
    public void setCanEatGhost(boolean canEatGhost)
    {
        this.canEatGhost = canEatGhost;
    }
}
