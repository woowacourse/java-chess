package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @DisplayName("나이트의 이동 벡터 리스트를 반환한다.")
    @Test
    void getKnightDirections() {
        List<Direction> knightDirections = List.of(
                Direction.NNE,
                Direction.NNW,
                Direction.SSE,
                Direction.SSW,
                Direction.EEN,
                Direction.EES,
                Direction.WWN,
                Direction.WWS
        );

        assertThat(Direction.getKnightDirections()).isEqualTo(knightDirections);
    }
}
