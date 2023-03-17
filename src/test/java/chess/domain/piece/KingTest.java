package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KingTest {


    @Test
    @DisplayName("Knight 은 앞뒤양옆 1칸 이동 후 대각선으로 1칸 이동 가능하다.")
    void 이동_범위_확인() {
        King king = new King(Camp.WHITE);

        CheckablePaths checkablePaths = king.findCheckablePaths(Position.of(1, 1));

        assertThat(checkablePaths.positionsSize()).isEqualTo(3);
    }

}
