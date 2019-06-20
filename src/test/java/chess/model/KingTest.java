package chess.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KingTest {
    
    private King king;

    @BeforeEach
    void setUp() {
        king = new King(ChessPieceColor.BLACK);
    }

    @Test
    void canMove_이동_가능_확인() {
        Point source = new Point(4, 4);
        Point target = new Point(3, 4);
        assertThat(king.canMove(source, target)).isTrue();
    }

    @Test
    void canMove_이동_불가능_확인() {
        Point source = new Point(4, 4);
        Point target = new Point(3, 6);
        assertThat(king.canMove(source, target)).isFalse();
    }
}
