package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import chess.domain.board.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @Test
    @DisplayName("Postion이 정상적으로 생성되어야 한다.")
    void create_Success() {
        // given
        Position position = Position.of(4, 6);

        // expect
        assertThat(position.getX())
                .isEqualTo(4);
        assertThat(position.getY())
                .isEqualTo(6);
    }

    @ParameterizedTest
    @CsvSource({"-1,-1", "8,8", "-1,3", "3,-1"})
    @DisplayName("잘못된 위치가 주어지면 예외가 발생해야 한다.")
    void create_Fail(int x, int y) {
        // expect
        Assertions.assertThatThrownBy(() -> Position.of(x, y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 잘못된 위치 값 입니다.");
    }

    @Test
    @DisplayName("거리가 정확히 계산되어야 한다.")
    void getDistanceTo_Success() {
        // given
        Position source = Position.of(4, 4);
        Position target = Position.of(5, 5);

        // expect
        assertThat(source.getDistanceTo(target))
                .isEqualTo(1);
    }

    @Test
    void getRouteBetweenSourceAndTargetPosition() {
        // given
        Position source = Position.of(3, 3);
        Position target = Position.of(6, 0);

        // when
        List<Position> routes = source.getBetweenPositions(target);

        // then
        assertThat(routes)
                .containsExactly(Position.of(4, 2), Position.of(5, 1));
    }
}
