package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.path.MovablePaths;
import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("Knight 은 앞뒤양옆 1칸 이동 후 대각선으로 1칸 이동 가능하다.")
    void 이동_범위_확인() {
        Knight knight = new Knight(TeamColor.WHITE);

        MovablePaths paths = knight.findMovablePaths(Position.of(2, 2));

        assertThat(paths.getTotalPositionCount()).isEqualTo(4);
    }

}
