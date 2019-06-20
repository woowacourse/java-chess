package domain;

import chess.domain.ChessBoard;
import chess.domain.Point;
import chess.domain.PointFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChessBoardTest {

    ChessBoard chessBoard;

    @BeforeEach
    void setup() {
        chessBoard = new ChessBoard();
    }

    @Test
    void 체스_보드_생성_Test() {
        assertDoesNotThrow(() -> new ChessBoard());
    }

    @Test
    void Knight_정상_이동_Test() {
        Point source = PointFactory.of("b1");
        Point target = PointFactory.of("c3");
        assertTrue(chessBoard.playOneStep(0, source, target));
    }

    @Test
    void Bishop_정상_이동_Test() {
        Point source = PointFactory.of("c1");
        Point target = PointFactory.of("e3");
        assertTrue(chessBoard.playOneStep(0, source, target));
    }

    @Test
    void King_정상_이동_Test() {
        Point source = PointFactory.of("e1");
        Point target = PointFactory.of("e2");
        assertTrue(chessBoard.playOneStep(0, source, target));
    }

    @Test
    void Queen_정상_이동_Test() {
        Point source = PointFactory.of("d1");
        Point target = PointFactory.of("d5");
        assertTrue(chessBoard.playOneStep(0, source, target));
    }

//    @Test
//    void Pawn_정상_이동_Test() {
//        Point source = PointFactory.of("b1");
//        Point target = PointFactory.of("c3");
//        assertTrue(chessBoard.playOneStep(0, source, target));
//    }

}
