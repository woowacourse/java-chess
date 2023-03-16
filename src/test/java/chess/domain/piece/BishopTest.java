package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Path;
import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @Test
    @DisplayName("Bishop 은 대각선으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Bishop bishop = new Bishop(TeamColor.WHITE);

        List<Path> movablePaths = bishop.findMovablePaths(Position.of(2, 2));

        int totalPositionCount = 0;

        for (Path path : movablePaths) {
            totalPositionCount += path.positions().size();
        }

        assertThat(totalPositionCount).isEqualTo(9);
    }

}
