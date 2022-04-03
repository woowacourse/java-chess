package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @Test
    @DisplayName("x,y 값이 같은 경우에는 같은 Position 객체임을 확인한다.")
    void isSamePosition() {
        Position position1 = Position.valueOf("a1");
        Position position2 = Position.valueOf("a1");
        assertThat(position1).isEqualTo(position2);
    }

    @Test
    @DisplayName("x,y 값이 다른 경우에는 다른 Position 객체임을 확인한다.")
    void isNotSamePosition() {
        Position position1 = Position.valueOf("a1");
        Position position2 = Position.valueOf("b1");
        assertThat(position1).isNotEqualTo(position2);
    }

    @ParameterizedTest
    @DisplayName("체스보드에 없는 좌표는 예외를 발생 시킨다.")
    @ValueSource(strings = {"i1", "a9", "i9"})
    void isNotExistPosition(String value) {
        assertThatThrownBy(() -> Position.valueOf(value))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 해당 포지션은 체스 보드에 존재하지 않습니다.");
    }
}
