package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.path.MovablePaths;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class RookTest {

    @Test
    @DisplayName("Rook 은 앞뒤양옆으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Rook rook = new Rook(TeamColor.WHITE);

        MovablePaths paths = rook.findMovablePaths(Position.of(1, 1));

        assertThat(paths.getTotalPositionCount()).isEqualTo(14);
    }
}
