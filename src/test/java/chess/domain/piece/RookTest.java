package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Camp;
import chess.domain.Path;
import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("Rook 은 앞뒤양옆으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Rook rook = new Rook(Camp.WHITE);

        List<Path> movablePaths = rook.findAllPaths(Position.of(1, 1));

        int totalPositionCount = 0;

        for (Path path : movablePaths) {
            totalPositionCount += path.positions().size();
        }

        assertThat(totalPositionCount).isEqualTo(14);
    }
}
