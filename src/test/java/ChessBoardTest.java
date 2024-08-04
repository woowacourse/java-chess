import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {
	
	private ChessBoard chessBoard;
	
	@BeforeEach
	public void setUp() {
		ChessBoard chessBoard = new ChessBoard();
	}

    @Test
    public void testChessBoard() {
        assertNotNull(chessBoard, "ChessBoard should be initialized");
    }
    
    @Test
    public void testChange() {
    	List<Integer> result = chessBoard.change("a3");
        List<Integer> expected = List.of(1, 3);
    	
    	assertEquals(expected, result, "The change method should return the correct substrings.");
    }
}
