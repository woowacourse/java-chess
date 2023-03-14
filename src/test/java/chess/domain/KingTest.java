package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KingTest {

    @Test
    @DisplayName("King은 앞뒤양옆, 대각선 한 칸 씩 이동 가능하다.")
    void 이동_범위_확인() {
        King king = new King();

        List<Position> movablePositions = king.findMovablePositions(new Position(1, 1));

        assertThat(movablePositions).containsExactlyInAnyOrder(new Position(1, 2), new Position(2, 1),
                new Position(2, 2));
    }

}
