package chess.model.piece;

import chess.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
    private Queen queen;

    @BeforeEach
    void setUp() {
        queen = new Queen(ChessPieceColor.BLACK);
    }

    @Test
    void 대각선_이동_가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        board.put(new Point(1, 1), queen);

        Point source = new Point(1, 1);
        Point target = new Point(8, 8);
        assertThat(queen.canMove(source, target, (Point p) -> board.get(p))).isTrue();
    }

    @Test
    void 수평_이동_가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        board.put(new Point(1, 1), queen);

        Point source = new Point(1, 1);
        Point target = new Point(8, 1);
        assertThat(queen.canMove(source, target, (Point p) -> board.get(p))).isTrue();
    }

    @Test
    void 대각선_이동_불가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        board.put(new Point(1, 1), queen);
        board.put(new Point(3, 3), new Rook(ChessPieceColor.WHITE));

        Point source = new Point(1, 1);
        Point target = new Point(8, 8);
        assertThat(queen.canMove(source, target, (Point p) -> board.get(p))).isFalse();
    }

    @Test
    void 수평_이동_불가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        board.put(new Point(1, 1), queen);
        board.put(new Point(3, 1), new Bishop(ChessPieceColor.WHITE));

        Point source = new Point(1, 1);
        Point target = new Point(8, 1);
        assertThat(queen.canMove(source, target, (Point p) -> board.get(p))).isFalse();
    }
}
