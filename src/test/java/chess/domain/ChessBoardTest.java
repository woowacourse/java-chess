package chess.domain;

import chess.domain.pieces.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {

    ChessBoard chessBoard;

    @BeforeEach
    void etUp() {
        chessBoard = new ChessBoard();
    }

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new ChessBoard());
    }

    @Test
    void source에_체스말이_있는_경우_Test() {
        Point source = PointFactory.of("d2");
        assertTrue(chessBoard.hasPiece(source));
    }

    @Test
    void source에_체스말이_없는_경우_Test() {
        Point source = PointFactory.of("d3");
        assertFalse(chessBoard.hasPiece(source));
    }

    @Test
    void 체스말의_색깔과_현재턴의_색깔이_같은_경우_Test() {
        Point source = PointFactory.of("d2");
        Color colorOfTurn= Color.WHITE;
        assertTrue(chessBoard.equalsColor(source,colorOfTurn));
    }

    @Test
    void 체스말의_색깔과_현재턴의_색깔과_다른_경우_Test(){
        Point source = PointFactory.of("d2");
        Color colorOfTurn= Color.BLACK;
        assertFalse(chessBoard.equalsColor(source,colorOfTurn));
    }

}
