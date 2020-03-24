package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {
    @DisplayName("포인트 생성")
    @Test
    void construct() {
        Point point = new Point("a", "1", new WhitePiece(PieceType.BISHOP));
        assertThat(point).isNotNull();
    }
}
