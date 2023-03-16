package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.MovablePaths;
import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @Test
    @DisplayName("Queen은 앞뒤양옆, 대각선으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Queen queen = new Queen(TeamColor.WHITE);

        MovablePaths paths = queen.findMovablePaths(Position.of(2, 2));

        assertThat(paths.getTotalPositionCount()).isEqualTo(23);
    }
}
