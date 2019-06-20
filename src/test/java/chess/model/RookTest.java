package chess.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    private Rook rook;

    @BeforeEach
    void setUp() {
        rook = new Rook(ChessPieceColor.BLACK);
    }

    @Test
    void 이동_가능_테스트() {
        Point source = new Point(3, 3);
        Point target = new Point(3, 8);
        assertThat(rook.canMove(source, target)).isTrue();
    }

    @Test
    void 이동_불가능_테스트() {
        Point source = new Point(3,3);
        Point target = new Point(4, 4);
        assertThat(rook.canMove(source, target)).isFalse();
    }
}
