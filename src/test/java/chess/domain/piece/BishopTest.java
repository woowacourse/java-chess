package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @Test
    @DisplayName("Bishop 은 대각선으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Bishop bishop = new Bishop(Camp.WHITE);

        CheckablePaths checkablePaths = bishop.findCheckablePaths(Position.of(2, 2));

        assertThat(checkablePaths.positionsSize()).isEqualTo(9);
    }

}
