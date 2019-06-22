package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    Game game = null;

    @BeforeEach
    public void setUp() {
        Board board = new Board(BoardGenerator.generate());
        game = Game.from(board);
    }

    @Test
    public void 다른_색깔의_말을_클릭할때() {
        Position origin = Position.of("7", "a");
        Position target = Position.of("6", "a");
        assertFalse(game.action(origin, target));
    }

    @Test
    public void 자기_색깔의_말을_클릭할때() {
        Position origin = Position.of("2", "a");
        Position target = Position.of("4", "a");
        assertTrue(game.action(origin, target));
    }

    @Test
    public void 턴이_제대로_바뀌었는지() {
        Position origin = Position.of("2", "a");
        Position target = Position.of("4", "a");
        game.action(origin, target);
        assertTrue(game.currentColor().equals(Piece.Color.BLACK.getName()));
    }

    @Test
    public void 실패했을때_턴이_그대로인지() {
        Position origin = Position.of("7", "a");
        Position target = Position.of("6", "a");
        game.action(origin, target);
        assertTrue(game.currentColor().equals(Piece.Color.WHITE.getName()));
    }

    @Test
    public void getSquaresExceptEmptyTest(){
        int expected = 32;
        assertEquals(expected, game.getSquaresExceptEmpty().size());
    }

}