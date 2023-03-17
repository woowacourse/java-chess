package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("Knight 은 앞뒤양옆 1칸 이동 후 대각선으로 1칸 이동 가능하다.")
    void 이동_범위_확인() {
        Knight knight = new Knight(Camp.WHITE);

        CheckablePaths checkablePaths = knight.findCheckablePaths(Position.of(2, 2));

        assertThat(checkablePaths.positionsSize()).isEqualTo(4);
    }

}
