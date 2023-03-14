package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecePointsTest {

    @Test
    @DisplayName("기물이 기본 위치에 배치된다.")
    void initialChessBoardTest() {
        PiecePoints piecePoints = new PiecePoints();

        assertThat(piecePoints.getPiecePoint()).hasSize(32);
    }
}
