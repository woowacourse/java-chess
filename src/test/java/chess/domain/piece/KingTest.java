package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.path.MovablePaths;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KingTest {


    @Test
    @DisplayName("Knight 은 앞뒤양옆 1칸 이동 후 대각선으로 1칸 이동 가능하다.")
    void 이동_범위_확인() {
        King king = new King(TeamColor.WHITE);

        MovablePaths paths = king.findMovablePaths(Position.from("A1"));

        assertThat(paths.getTotalPositionCount()).isEqualTo(3);
    }

}
