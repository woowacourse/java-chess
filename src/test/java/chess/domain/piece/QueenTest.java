package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Path;
import chess.domain.TeamColor;
import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @Test
    @DisplayName("Queen은 앞뒤양옆, 대각선으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Queen queen = new Queen(TeamColor.WHITE);

        List<Path> movablePaths = queen.findMovablePaths(Position.of(2, 2));

        int totalPositionCount = 0;

        for (Path path : movablePaths) {
            totalPositionCount += path.positions().size();
        }

        assertThat(totalPositionCount).isEqualTo(23);
    }
}
