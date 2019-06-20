package chess.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

    Knight knight;

    @BeforeEach
    void setUp() {
        knight = new Knight(ChessPieceColor.BLACK);
    }

    @Test
    void 이동_가능_테스트() {
        Point source = new Point(4, 4);
        Point target = new Point(5, 6);
        assertThat(knight.canMove(source, target)).isTrue();
    }

    @Test
    void 이동_불가능_테스트() {
        Point source = new Point(4, 4);
        Point target = new Point(5, 5);
        assertThat(knight.canMove(source, target)).isFalse();
    }
}
