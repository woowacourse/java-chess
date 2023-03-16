package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Path;
import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("Knight 은 앞뒤양옆 1칸 이동 후 대각선으로 1칸 이동 가능하다.")
    void 이동_범위_확인() {
        Knight knight = new Knight(TeamColor.WHITE);

        List<Path> movablePaths = knight.findAllPaths(Position.of(2, 2));

        int totalPositionCount = 0;

        for (Path path : movablePaths) {
            totalPositionCount += path.positions().size();
        }

        assertThat(totalPositionCount).isEqualTo(4);
    }

}
