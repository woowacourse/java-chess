package chess.model.piece;

import chess.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    private Rook rook;

    @BeforeEach
    void setUp() {
        rook = new Rook(ChessPieceColor.BLACK);
    }

    @Test
    void 이동_가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        board.put(new Point(1, 1), rook);

        Point source = new Point(1, 1);
        Point target = new Point(8, 1);
        assertThat(rook.canMove(source, target, (Point p) -> board.get(p))).isTrue();
    }

    @Test
    void 이동_불가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        board.put(new Point(1, 1), rook);
        board.put(new Point(5, 1), new King(ChessPieceColor.WHITE));

        Point source = new Point(1, 1);
        Point target = new Point(8, 1);
        assertThat(rook.canMove(source, target, (Point p) -> board.get(p))).isFalse();
    }
}
