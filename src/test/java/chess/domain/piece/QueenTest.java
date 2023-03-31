package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.path.MovablePaths;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class QueenTest {

    @Test
    @DisplayName("Queen은 앞뒤양옆, 대각선으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Queen queen = new Queen(TeamColor.WHITE);

        MovablePaths paths = queen.findMovablePaths(Position.from("B2"));

        assertThat(paths.getTotalPositionCount()).isEqualTo(23);
    }
}
