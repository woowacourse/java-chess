package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("비숍의 이동 벡터 리스트를 반환한다.")
    @Test
    void getBishopDirections() {
        List<Direction> bishopDirections = List.of(
                Direction.NORTHEAST,
                Direction.SOUTHEAST,
                Direction.SOUTHWEST,
                Direction.NORTHWEST
        );

        assertThat(Direction.getBishopDirections()).isEqualTo(bishopDirections);
    }

    @DisplayName("이동 가능한 경로일 경우 방향을 반환한다.")
    @Test
    void findDirection() {
        assertThat(Direction.findDirection(
                new Position("a1"),
                new Position("h8")
        )).isEqualTo(Direction.NORTHEAST);
    }

    @DisplayName("이동 가능한 경로일 경우 방향을 반환한다.")
    @Test
    void findDirection_Fails() {
        assertThatThrownBy(() -> Direction.findDirection(
                new Position("a1"),
                new Position("g8")
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 가는 방향을 찾을 수 없습니다.");
    }
}
