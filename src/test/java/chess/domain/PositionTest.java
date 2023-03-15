package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PositionTest {

    @ParameterizedTest
    @CsvSource(value = {"0:1", "1:0", "9:1", "1:9"}, delimiter = ':')
    @DisplayName("Position 은 각각 1 ~ 8을 넘길 수 없다.")
    void createPositionFail(int x, int y) {
        assertThatThrownBy(() -> new Position(x, y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Position.EXCEPTION_MESSAGE_OUT_OF_BOUNDS);
    }

    @Test
    @DisplayName("Position 이 1 ~ 8 이내이면 생성이 이루어진다.")
    void createPositionSuccess() {
        assertDoesNotThrow(() -> new Position(1, 8));
    }

    @Test
    @DisplayName("전달 받은 방향에 따른 다음 위치를 반환한다.")
    void 다음_이동_위치_반환() {
        Position current = new Position(1, 1);

        Position next = current.findNextPosition(Direction.NORTH_EAST);

        assertThat(next).isEqualTo(new Position(2, 2));
    }

    @Test
    @DisplayName("전달 받은 방향에 따른 다음 위치가 범위 밖이라면, null 이 반환된다.")
    void 다음_이동_불가() {
        Position current = new Position(1, 8);

        Position next = current.findNextPosition(Direction.EAST);

        assertThat(next).isNull();
    }

}
