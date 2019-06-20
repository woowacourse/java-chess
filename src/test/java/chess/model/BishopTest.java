package chess.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {

    Bishop bishop;

    @BeforeEach
    void setUp() {
        bishop = new Bishop(ChessPieceColor.WHITE);
    }

    @Test
    void 이동_가능_테스트() {
        Point source = new Point(3, 3);
        Point target = new Point(8, 8);
        assertThat(Bishop.canMove(source, target)).isTrue();
    }

    @Test
    void 이동_불가능_테스트() {
        Point source = new Point(3, 3);
        Point target = new Point(3, 8);
        assertThat(Bishop.canMove(source, target)).isFalse();
    }

}
