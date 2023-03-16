package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.MovablePaths;
import chess.domain.Path;
import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KingTest {


    @Test
    @DisplayName("Knight 은 앞뒤양옆 1칸 이동 후 대각선으로 1칸 이동 가능하다.")
    void 이동_범위_확인() {
        King king = new King(TeamColor.WHITE);

        MovablePaths paths = king.findMovablePaths(Position.of(1, 1));

        assertThat(paths.getTotalPositionCount()).isEqualTo(3);
    }

}
