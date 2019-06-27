package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.exceptions.IllegalSourceException;
import chess.domain.exceptions.InvalidRouteException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessBoardTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
    }

    @Test
    void 말이_없는_경우_예외_테스트() {
        Position source = new Position(new Coordinate('b'), new Coordinate(4));
        Position target = new Position(new Coordinate('b'), new Coordinate(5));

        assertThrows(IllegalSourceException.class, () -> chessBoard.move(source, target));
    }

    @Test
    void source위치와_target의_말_팀이_같은_경우_테스트() {
        Position source = new Position(new Coordinate('b'), new Coordinate(4));
        Position target = new Position(new Coordinate('b'), new Coordinate(5));

        assertThrows(IllegalSourceException.class, () -> chessBoard.move(source, target));
    }

    @Test
    void 룩_경로에_말이_있는지_테스트() {
        Position source = new Position(new Coordinate('a'), new Coordinate(1)); // Team.WHITE Rook
        Position target = new Position(new Coordinate('a'), new Coordinate(8));

        assertThrows(InvalidRouteException.class, () -> chessBoard.move(source, target));
    }

    @Test
    void 비숍_경로에_말이_있는지_테스트() {
        Position source = new Position(new Coordinate('c'), new Coordinate(1)); // Team.WHITE Bishop
        Position target = new Position(new Coordinate('e'), new Coordinate(3));

        assertThrows(InvalidRouteException.class, () -> chessBoard.move(source, target));
    }

    @Test
    void 퀸_경로에_말이_있는지_테스트() {
        Position source = new Position(new Coordinate('d'), new Coordinate(1)); // Team.WHITE Queen
        Position target = new Position(new Coordinate('d'), new Coordinate(3));

        assertThrows(InvalidRouteException.class, () -> chessBoard.move(source, target));
    }

    @Test
    void 연속으로_같은_팀이_진행하는_경우_테스트() {
        Position source1 = new Position(new Coordinate('b'), new Coordinate(2)); // Team.WHITE Rook
        Position target1 = new Position(new Coordinate('b'), new Coordinate(3));
        Position source2 = new Position(new Coordinate('c'), new Coordinate(2)); // Team.WHITE Rook
        Position target2 = new Position(new Coordinate('c'), new Coordinate(3));

        chessBoard.move(source1, target1);
        assertThrows(IllegalSourceException.class, () -> chessBoard.move(source2, target2));
    }

    @Test
    void 선공이_아닌_black팀이_먼저_진행하는_경우_테스트() {
        Position source = new Position(new Coordinate('b'), new Coordinate(7)); // Team.BLACK Rook
        Position target = new Position(new Coordinate('b'), new Coordinate(6));

        assertThrows(IllegalSourceException.class, () -> chessBoard.move(source, target));
    }
}
