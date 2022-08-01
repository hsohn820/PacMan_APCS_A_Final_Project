/**
 *
 * The class location is also a very basic class that checks if the rows equal
 * to the columns.
 *
 * Location just initilizes row and column  and then checks whether they're
 * equivalent to each other or not. Location also has a toString method. It
 * works as any other basic string returner.
 *
 *  @author Bedri, Richie
 *  @version 2021. 5. 27.
 */
public class Location
{
    private int row, col;
    /**
     * Create a new Location object.
     * @param r row
     * @param c column
     */
    public Location(int r, int c)
    {
        row = r;
        col = c;
    }

    /**
     * getRow is a getter for the row.
     * @return row for the getter
     */
    public int getRow()
    {
        return row;
    }

    /**
     * getCol is a getter for the Col
     * @return col for the getter
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Equals is a method that checks whether the two location contain same row and col
     * @param other the other Location object
     * @return it returns a boolean depending on the trueness of the statement.
     */
    public boolean equals(Location other)
    {
        return other.getRow() == row && other.getCol() == col;
    }

    /**
     * {@inheritDoc}
     */
    public String toString()
    {
        return "(" + row + ", " + col + ")";
    }
}
