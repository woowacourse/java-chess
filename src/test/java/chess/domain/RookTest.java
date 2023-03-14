package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RookTest {

    @Test
    @DisplayName("Rook 은 앞뒤양옆으로 원하는 만큼 이동 가능하다.")
    void 이동_범위_확인() {
        Rook rook = new Rook();

        List<Position> movablePositions = rook.findMovablePositions(new Position(2, 2));

        assertThat(movablePositions).contains(new Position(2, 1), new Position(1, 2), new Position(2, 3),
                new Position(3, 2), new Position(8, 2), new Position(2, 8));

        assertThat(movablePositions.size()).isEqualTo(14);
    }
}
