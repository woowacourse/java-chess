package chess.domain;

import chess.domain.exceptions.IllegalSourceException;
import chess.domain.exceptions.InvalidRouteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chess.domain.utils.InputParser.position;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessBoardTest {
    ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
    }

    @Test
    void 말이_없는_경우_예외_테스트() {
        Position source = position("b4");
        Position target = position("b5");

        assertThrows(IllegalSourceException.class, () -> chessBoard.move(source, target));
    }

    @Test
    void source위치와_target의_말_팀이_같은_경우_테스트() {
        Position source = position("b4");
        Position target = position("b5");

        assertThrows(IllegalSourceException.class, () -> chessBoard.move(source, target));
    }

    @Test
    void 룩_경로에_말이_있는지_테스트() {
        Position source = position("a1");
        Position target = position("a8");

        assertThrows(InvalidRouteException.class, () -> chessBoard.move(source, target));
    }

    @Test
    void 비숍_경로에_말이_있는지_테스트() {
        Position source = position("c1");
        Position target = position("e3");

        assertThrows(InvalidRouteException.class, () -> chessBoard.move(source, target));
    }

    @Test
    void 퀸_경로에_말이_있는지_테스트() {
        Position source = position("d1");
        Position target = position("d3");

        assertThrows(InvalidRouteException.class, () -> chessBoard.move(source, target));
    }

    @Test
    void 연속으로_같은_팀이_진행하는_경우_테스트() {
        Position firstSource = position("b2");
        Position firstTarget = position("b3");
        Position secondSource = position("c2");
        Position secondTarget = position("c3");

        chessBoard.move(firstSource, firstTarget);
        assertThrows(IllegalSourceException.class, () -> chessBoard.move(secondSource, secondTarget));
    }

    @Test
    void 선공이_아닌_black팀이_먼저_진행하는_경우_테스트() {
        Position source = position("b7");
        Position target = position("b6");

        assertThrows(IllegalSourceException.class, () -> chessBoard.move(source, target));
    }
}
