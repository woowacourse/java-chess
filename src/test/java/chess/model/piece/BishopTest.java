package chess.model.piece;

import chess.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {

    Bishop bishop;

    @BeforeEach
    void setUp() {
        bishop = new Bishop(ChessPieceColor.WHITE);
    }

    @Test
    void 이동_가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        board.put(new Point(8, 8), bishop);

        Point source = new Point(8, 8);
        Point target = new Point(1, 1);
        assertThat(bishop.canMove(source, target, (Point p) -> board.get(p))).isTrue();
    }

    @Test
    void 이동_불가능_테스트() {
        Map<Point, AbstractChessPiece> board = new HashMap<>();
        board.put(new Point(8, 8), bishop);
        board.put(new Point(5, 5), new King(ChessPieceColor.WHITE));

        Point source = new Point(8, 8);
        Point target = new Point(1, 1);
        assertThat(bishop.canMove(source, target, (Point p) -> board.get(p))).isFalse();
    }
}
