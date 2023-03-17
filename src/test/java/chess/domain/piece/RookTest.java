package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("Rook 은 앞뒤양옆으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Rook rook = new Rook(Camp.WHITE);

        CheckablePaths checkablePaths = rook.findCheckablePaths(Position.of(1, 1));

        assertThat(checkablePaths.positionsSize()).isEqualTo(14);
    }
}
