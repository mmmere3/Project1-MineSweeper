import cs2114.minesweeper.MineSweeperBoard;
import cs2114.minesweeper.MineSweeperCell;
import junit.framework.TestCase;

/**
 * Unit tests for the MineSweeperBoard class.
 *
 * @author Meredith McGlynn mmmere3
 * @version 2012.09.15
 */
public class MineSweeperBoardTest
    extends TestCase
{
    private MineSweeperBoard board;


    /**
     * Creates a new MineSweeperBoardTest object.
     */
    public MineSweeperBoardTest()
    {
        // This is supposed to be empty, as constructors are empty in test
// classes
    }


    /**
     * This sets up a test fixture, which is called in almost every test method.
     * It creates a new mine sweeper board with parameters of my choosing
     */
    public void setUp()
    {
        board = new MineSweeperBoard(4, 4, 2);
    }


    /**
     * This method tests the width() method to see if it'll return the width of
     * a board with a given width.
     */
    public void testWidth()
    {
        assertEquals(board.width(), 4);
    }


    /**
     * This method tests the height() method by seeing if it'll return the
     * height of a board with a given height.
     */
    public void testHeight()
    {
        assertEquals(board.height(), 4);
    }


    /**
     * This method tests the getCell(int x, int y) method by setting a cell to a
     * certain MineSweeperCell type and then calling getCell to see if it
     * returns the same set type.
     */
    public void testGetCell()
    {
        board.setCell(1, 2, MineSweeperCell.FLAGGED_MINE);
        assertEquals(board.getCell(1, 2), MineSweeperCell.FLAGGED_MINE);
    }


    /**
     * This method asserts that an expected board is the same as a given board.
     *
     * @param theBoard
     *            the board being tested to see if it is similar to a new board
     * @param expected
     *            a String
     */
    public void assertBoard(MineSweeperBoard theBoard, String... expected)
    {
        MineSweeperBoard expectedBoard =
            new MineSweeperBoard(expected.length, expected[0].length(), 0);
        expectedBoard.loadBoardState(expected);
        assertEquals(expectedBoard, theBoard);
        // uses equals() from MineSweeperBoardBase
    }


    /**
     * This method tests the setCell(int x, int y) method by creating a board,
     * setting a cell to be a flagged mine, and then asserting that the board
     * reflects this change.
     */
    public void testSetCell()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ", "OOOO", "O++O", "OOOO");

        board.setCell(1, 2, MineSweeperCell.FLAGGED_MINE);

        assertBoard(board, "    ", "OOOO", "OM+O", "OOOO");
    }


    /**
     * This method tests the flagCell(int x, int y) method by setting a cell to
     * hold a covered mine, then calling the flagCell method, and asserting that
     * the cell is now considered a flagged mine.
     */
    public void testFlagCell()
    {
        board.setCell(3, 3, MineSweeperCell.MINE);
        board.flagCell(3, 3);
        board.setCell(2, 2, MineSweeperCell.COVERED_CELL);
        board.flagCell(2, 2);
        assertEquals(board.getCell(2, 2), MineSweeperCell.FLAG);
        assertEquals(board.getCell(3, 3), MineSweeperCell.FLAGGED_MINE);
    }


    /**
     * This cases tests the isGameLost() method, by creating a situation in
     * which the game is lost, and seeing if the method returns a true boolean.
     */
    public void testIsGameLost()
    {
        board.setCell(3, 3, MineSweeperCell.UNCOVERED_MINE);
        assertTrue(board.isGameLost());
    }


    /**
     * This case tests the isGameWon() method, by creating a situation in which
     * the game is lost, and seeing if the method returns a false boolean.
     */
    public void testIsGameWon()
    {
        board.setCell(2, 2, MineSweeperCell.UNCOVERED_MINE);
        assertFalse(board.isGameWon());
    }


    /**
     * This case tests the numberOfAdjacentMines(int x, int y) method. It sets
     * the cells around a given cell to either flagged mines, incorrectly
     * flagged cells, covered cells, or the number of adjacent mines there are,
     * and returns the number of mines adjacent to a given cell.
     */
    public void testNumAdjMines()
    {
        board.setCell(1, 1, MineSweeperCell.COVERED_CELL);
        board.setCell(2, 1, MineSweeperCell.MINE);
        board.setCell(3, 1, MineSweeperCell.ADJACENT_TO_2);
        board.setCell(1, 2, MineSweeperCell.ADJACENT_TO_1);
        board.setCell(3, 2, MineSweeperCell.FLAGGED_MINE);
        board.setCell(1, 3, MineSweeperCell.FLAG);
        board.setCell(2, 3, MineSweeperCell.COVERED_CELL);
        board.setCell(3, 3, MineSweeperCell.COVERED_CELL);
        assertEquals(board.numberOfAdjacentMines(2, 2), 2);
    }


    // ----------------------------------------------------------
    /**
     * Additional testing for the numberOfAdjacentMines function, to see if all
     * mines will be caught
     */
    public void testNumAdjMinesAllMines()
    {
        board.setCell(1, 1, MineSweeperCell.MINE);
        board.setCell(2, 1, MineSweeperCell.COVERED_CELL);
        board.setCell(3, 1, MineSweeperCell.MINE);
        board.setCell(1, 2, MineSweeperCell.MINE);
        board.setCell(3, 2, MineSweeperCell.MINE);
        board.setCell(1, 3, MineSweeperCell.MINE);
        board.setCell(2, 3, MineSweeperCell.MINE);
        board.setCell(3, 3, MineSweeperCell.MINE);
        assertEquals(board.numberOfAdjacentMines(2, 2), 7);
    }


    // ----------------------------------------------------------
    /**
     * Additional testing for the numberOfAdjacentMines method, for flagged mine
     * cells.
     */
    public void testNumAdjMinesFlaggedMines()
    {
        board.setCell(1, 1, MineSweeperCell.FLAGGED_MINE);
        board.setCell(2, 1, MineSweeperCell.FLAGGED_MINE);
        board.setCell(3, 1, MineSweeperCell.FLAGGED_MINE);
        board.setCell(1, 2, MineSweeperCell.FLAGGED_MINE);
        board.setCell(3, 2, MineSweeperCell.FLAGGED_MINE);
        board.setCell(1, 3, MineSweeperCell.FLAGGED_MINE);
        board.setCell(2, 3, MineSweeperCell.FLAGGED_MINE);
        board.setCell(3, 3, MineSweeperCell.FLAGGED_MINE);
        assertEquals(board.numberOfAdjacentMines(2, 2), 8);
    }


    /**
     * This case tests the uncoverCell(int x, int y) method. It sets a cell to a
     * covered mine, then uncovers it, and tests to see if the uncovered cell is
     * registered as an uncovered mine.
     */
    public void testUncoverCell()
    {
        board.fillBoard();
        board.setCell(1, 2, MineSweeperCell.MINE);
        board.uncoverCell(1, 2);
        assertEquals(board.getCell(1, 2), MineSweeperCell.UNCOVERED_MINE);
        board.revealBoard();
    }
}
