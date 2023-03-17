package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @Test
    @DisplayName("Queen은 앞뒤양옆, 대각선으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Queen queen = new Queen(Camp.WHITE);

        CheckablePaths checkablePaths = queen.findCheckablePaths(Position.of(2, 2));

        assertThat(checkablePaths.positionsSize()).isEqualTo(23);
    }
}
