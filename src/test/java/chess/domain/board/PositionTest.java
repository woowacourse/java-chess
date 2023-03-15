package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @Test
    @DisplayName("정상적인 위치면 예외를 발생시키지 않는다.")
    void throws_not_exception_when_case_is_normally() {
        // when & then
        assertDoesNotThrow(
                () -> new Position("b2")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Z2", "z2", "m0", "a0","z1"})
    @DisplayName("잘못된 위치면 예외를 발생시킨다.")
    void throws_exception_when_case_is_invalid(final String input) {
        // when & then
        assertThatThrownBy(
                () -> new Position(input)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동하고자 하는 위치와 현재 위치의 Row 차이를 반환한다.")
    void returns_sub_row_from_arrive_row() {
        // given
        Position nowPosition = new Position("c3");
        String arrivePosition = "c4";

        // when
        int result = nowPosition.subRowFromArriveRow(arrivePosition);

        // then
        assertThat(result).isEqualTo(1);
     }

    @Test
    @DisplayName("이동하고자 하는 위치와 현재 위치의 Row 차이를 반환한다.")
    void returns_sub_col_from_arrive_col() {
        // given
        Position nowPosition = new Position("c3");
        String arrivePosition = "d3";

        // when
        int result = nowPosition.subColFromArriveCol(arrivePosition);

        // then
        assertThat(result).isEqualTo(1);
    }
}
