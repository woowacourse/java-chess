package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KnightTest {

    @Test
    @DisplayName("Knight 은 앞뒤양옆 1칸 이동 후 대각선으로 1칸 이동 가능하다.")
    void 이동_범위_확인() {
        Knight knight = new Knight();

        List<Position> movablePositions = knight.findMovablePositions(new Position(2, 2));

        assertThat(movablePositions).contains(new Position(4, 1), new Position(4, 3), new Position(3, 4),
                new Position(1, 4));

        assertThat(movablePositions.size()).isEqualTo(4);
    }
}
