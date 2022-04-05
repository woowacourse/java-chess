package refactorChess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @Test
    @DisplayName("포지션의 좌표를 String 값을 통해서 받아 올 수 있다.")
    void valueOfValidPositionToString() {
        assertThat(Position.valueOf("a1")).isInstanceOf(Position.class);
    }

    @Test
    @DisplayName("체스판이 가질 수 있는 64개의 위치를 캐싱할 수 있다.")
    void createValidCashingToPosition() {
        assertThat(Position.valueOf("a1")).isEqualTo(Position.valueOf("a1"));
    }

    @ParameterizedTest
    @CsvSource(value = {"a0, i1, a9"})
    @DisplayName("체스판이 가질 수 있는 포지션을 벗어난 경우 예외가 발생한다.")
    void valueOfInvalidPositionToString(String value) {
        assertThatThrownBy(() -> Position.valueOf(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("범위를 벗어난 값 입니다.");
    }
}
