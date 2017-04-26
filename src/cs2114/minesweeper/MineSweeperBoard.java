package cs2114.minesweeper;

//import student.TestableRandom;
import sofia.util.Random;

// -------------------------------------------------------------------------
/**
 * This creates a MineSweeper game, by creating a board and reacting to the user
 * clicking on cells and flagging mines.
 *
 * @author Meredith McGlynn mmmere3
 * @version 2012.09.15
 */
public class MineSweeperBoard
    extends MineSweeperBoardBase
{
    private int                 width;
    private int                 height;
    private int                 mineNumber;
    private MineSweeperCell[][] cellArray;
    private Random              randomCell;
    private boolean             losing;
    private boolean             winning;


    /**
     * This is the constructor that organizes the game by creating a board of a
     * given height and width and filling it randomly with a given number of
     * mines.
     *
     * @param width
     *            the integer width of the board in cells.
     * @param height
     *            the integer height of the board in cells.
     * @param mineNumber
     *            the integer number of mines the users wants in the game.
     */
    public MineSweeperBoard(int width, int height, int mineNumber)
    {
        randomCell = new Random();
        this.width = width;
        this.height = height;
        this.mineNumber = mineNumber;
        cellArray = new MineSweeperCell[width][height];
        fillBoard();
    }


    /**
     * This class will flag cells when clicked. If it's a mine, it will recorded
     * in the program as a flagged mine. If it's an empty cell, it will still be
     * flagged, but it will be incorrect
     *
     * @param x
     *            x is the x value of the board
     * @param y
     *            y is the y value of the board
     */
    public void flagCell(int x, int y)
    {
        if (cellArray[x][y] == MineSweeperCell.COVERED_CELL)
        {
            setCell(x, y, MineSweeperCell.FLAG);
        }
        if (cellArray[x][y] == MineSweeperCell.MINE)
        {
            setCell(x, y, MineSweeperCell.FLAGGED_MINE);
        }
    }


    /**
     * getCell gets the cell position in the array and returns that.
     *
     * @param x
     *            x is the x position on the board.
     * @param y
     *            y is the y position of the board.
     * @return the cellArray position of x and y.
     */
    public MineSweeperCell getCell(int x, int y)
    {
        if (x < 0 || x > width - 1)
        {
            return MineSweeperCell.INVALID_CELL;
        }
        if (y < 0 || y > height - 1)
        {
            return MineSweeperCell.INVALID_CELL;
        }
        else
        {
            return cellArray[x][y];
        }
    }


    /**
     * This returns the height as an integer.
     *
     * @return height returns the height of a given object
     */
    public int height()
    {
        return height;
    }


    /**
     * isGameLost() goes through the board and decides if the game is lost
     *
     * @return losing returns the boolean losing which says if the game is lost
     *         or not
     */
    public boolean isGameLost()
    {
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                if (cellArray[x][y] == MineSweeperCell.UNCOVERED_MINE)
                {
                    losing = true;
                }
                else
                {
                    losing = false;
                }
            }
        }
        return losing;
    }


    /**
     * isGameWon() determines if the game is won or not
     *
     * @return winning returns the boolean winning that is true if the game is
     *         won.
     */
    public boolean isGameWon()
    {
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                if (cellArray[x][y] == MineSweeperCell.UNCOVERED_MINE)
                {
                    winning = false;
                }
                if (cellArray[x][y] == MineSweeperCell.MINE)
                {
                    winning = false;
                }
                if (cellArray[x][y] == MineSweeperCell.COVERED_CELL)
                {
                    winning = false;
                }
                else
                {
                    winning = true;
                }
            }
        }
        return winning;
    }


    /**
     * numberOfAdjacentMines finds the number of adjacent mines surrounding the
     * given coordinates
     *
     * @param x
     *            The x coordinate of the cell on the board as an integer
     * @param y
     *            The y coordinate of the cell on the board as an integer
     * @return id id is an integer representing the number of mines surrounding
     *         the cell
     */
    public int numberOfAdjacentMines(int x, int y)
    {
        int id = 0;
        if (getCell(x - 1, y) == MineSweeperCell.MINE)
        {
            id = id + 1;
        }
        if (getCell(x - 1, y - 1) == MineSweeperCell.MINE)
        {
            id = id + 1;
        }
        if (getCell(x, y - 1) == MineSweeperCell.MINE)
        {
            id = id + 1;
        }
        if (getCell(x - 1, y + 1) == MineSweeperCell.MINE)
        {
            id = id + 1;
        }
        if (getCell(x + 1, y) == MineSweeperCell.MINE)
        {
            id = id + 1;
        }
        if (getCell(x + 1, y - 1) == MineSweeperCell.MINE)
        {
            id = id + 1;
        }
        if (getCell(x + 1, y + 1) == MineSweeperCell.MINE)
        {
            id = id + 1;
        }
        if (getCell(x, y + 1) == MineSweeperCell.MINE)
        {
            id = id + 1;
        }
        if (getCell(x - 1, y) == MineSweeperCell.FLAGGED_MINE)
        {
            id = id + 1;
        }
        if (getCell(x - 1, y - 1) == MineSweeperCell.FLAGGED_MINE)
        {
            id = id + 1;
        }
        if (getCell(x, y - 1) == MineSweeperCell.FLAGGED_MINE)
        {
            id = id + 1;
        }
        if (getCell(x - 1, y + 1) == MineSweeperCell.FLAGGED_MINE)
        {
            id = id + 1;
        }
        if (getCell(x + 1, y) == MineSweeperCell.FLAGGED_MINE)
        {
            id = id + 1;
        }
        if (getCell(x + 1, y - 1) == MineSweeperCell.FLAGGED_MINE)
        {
            id = id + 1;
        }
        if (getCell(x + 1, y + 1) == MineSweeperCell.FLAGGED_MINE)
        {
            id = id + 1;
        }
        if (getCell(x, y + 1) == MineSweeperCell.FLAGGED_MINE)
        {
            id = id + 1;
        }
        return id;
    }


    /**
     * revealBoard() uncovers all the cells on the board
     */
    public void revealBoard()
    {

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                uncoverCell(x, y);
            }
        }
    }


    /**
     * setCell sets a cell on a specific coordinate as a certain MineSweeperCell
     * type
     *
     * @param x
     *            x is the x-coordinate of the cell on the board
     * @param y
     *            y is the y-coordinate of the cell on the board
     * @param cell
     *            cell is the type of MineSweeperCell the cell will be changed
     *            into
     */
    public void setCell(int x, int y, MineSweeperCell cell)
    {
        cellArray[x][y] = cell;
    }


    /**
     * uncoverCell reveals the covered cell to be either a mine or an empty cell
     * with the number of adjacent mines
     *
     * @param x
     *            x is the x-coordinate of the cell on the board
     * @param y
     *            y is the y-coordinate of the cell on the board
     */
    public void uncoverCell(int x, int y)
    {
        if (cellArray[x][y] == MineSweeperCell.MINE)
        {
            setCell(x, y, MineSweeperCell.UNCOVERED_MINE);
        }
        if (cellArray[x][y] == MineSweeperCell.COVERED_CELL)
        {
            int adjMineNumber = numberOfAdjacentMines(x, y);
            setCell(x, y, MineSweeperCell.adjacentTo(adjMineNumber));
        }
    }


    /**
     * This will find and return the width of the board.
     *
     * @return returns the width of whatever it tries to find.
     */
    public int width()
    {
        return width;
    }


    /**
     * fillBoard() will fill an empty board of given height and width with the
     * given number of mines places randomly, then surrounded by covered empty
     * cells.
     */
    public void fillBoard()
    {
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                if (mineNumber > 0)
                {
                    int cellWidth = randomCell.nextInt(width);
                    int cellHeight = randomCell.nextInt(height);
                    setCell(cellWidth, cellHeight, MineSweeperCell.MINE);
                    mineNumber = mineNumber - 1;
                }
                else if (cellArray[x][y] != MineSweeperCell.MINE)
                {
                    setCell(x, y, MineSweeperCell.COVERED_CELL);
                }
            }
        }
    }

}
