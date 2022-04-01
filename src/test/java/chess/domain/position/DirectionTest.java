package chess.domain.position;

import chess.domain.board.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DirectionTest {
    @DisplayName("position 으로 방향 구하기 존재하는 방향")
    @ParameterizedTest
    @CsvSource(value = {"a1,b2,NORTH_EAST", "a1,b3,NORTH_NORTH_EAST"})
    void getDirection(final Position from, final Position to, final Direction expected) {
        assertThat(Direction.of(from, to)).isEqualTo(expected);
    }

    @DisplayName("position 으로 방향 구하기 존재하지 않는 방향일 경우 예외발생")
    @ParameterizedTest
    @CsvSource(value = {"a1,e3"})
    void getDirection2(final Position from, final Position to) {
        assertThatThrownBy(() -> Direction.of(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정의되지 않은 방향입니다.");
    }
}
