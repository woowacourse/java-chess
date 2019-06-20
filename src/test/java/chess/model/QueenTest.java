package chess.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
    private Queen queen;

    @BeforeEach
    void setUp() {
        queen = new Queen(ChessPieceColor.BLACK);
    }

    @Test
    void 대각선_이동_가능_테스트() {
        Point source = new Point(4, 4);
        Point target = new Point(8, 8);
        assertThat(queen.canMove(source, target)).isTrue();
    }

    @Test
    void 수평_이동_가능_테스트() {
        Point source = new Point(4, 4);
        Point target = new Point(8, 4);
        assertThat(queen.canMove(source, target)).isTrue();
    }

    @Test
    void 이동_불가능_테스트() {
        Point source = new Point(4, 4);
        Point target = new Point(6, 5);
        assertThat(queen.canMove(source, target)).isFalse();
    }
}
