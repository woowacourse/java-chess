package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @Test
    @DisplayName("Queen은 앞뒤양옆, 대각선으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Queen queen = new Queen();

        List<Position> movablePositions = queen.findMovablePositions(new Position(2, 2));

        assertThat(movablePositions).contains(new Position(1, 1), new Position(2, 1), new Position(3, 1),
                new Position(1, 2), new Position(1, 3), new Position(2, 3), new Position(3, 3), new Position(3, 2),
                new Position(8, 2), new Position(8, 8), new Position(2, 8));

        assertThat(movablePositions.size()).isEqualTo(23);
    }
}
