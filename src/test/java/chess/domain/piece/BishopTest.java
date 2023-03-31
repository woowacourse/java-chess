package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.path.MovablePaths;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class BishopTest {

    @Test
    @DisplayName("Bishop 은 대각선으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Bishop bishop = new Bishop(TeamColor.WHITE);

        MovablePaths paths = bishop.findMovablePaths(Position.from("B2"));

        assertThat(paths.getTotalPositionCount()).isEqualTo(9);
    }

}
