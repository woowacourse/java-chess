package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Column;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @Nested
    @DisplayName("생성자는")
    class Constructor {

        @Test
        @DisplayName("포지션을 생성한다.")
        void constructPosition() {
            assertThatCode(() -> new Position(Column.A, Row.ONE))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("포지션을 문자열로 생성한다.")
        void constructPositionByString() {
            Position position = new Position("a1");
            assertThat(position).isEqualTo(new Position(Column.A, Row.ONE));
        }

        @ParameterizedTest
        @ValueSource(strings = {"i1", "a9", "aa"})
        @DisplayName("생성할 때 지정된 문자열 외의 문자열이 들어오면 안된다.")
        void throwOutOfRange(String value) {
            assertThatThrownBy(() -> new Position(value))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {" ", ""})
        @DisplayName("생성할 때 빈 문자열이나 공백이 들어오면 안된다.")
        void throwBlank(String value) {
            assertThatThrownBy(() -> new Position(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("공백 또는 빈 문자열이면 안됩니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"a", "a11"})
        @DisplayName("생성할 때 길이가 2가 아니면 안된다.")
        void throwLength(String value) {
            assertThatThrownBy(() -> new Position(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("포지션은 두 글자입니다.");
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"EAST,e4", "NORTH,d5", "NORTH_EAST,e5"})
    @DisplayName("Direction에 따라서 움직인 후의 Position을 얻을 수 있다.")
    void toDirection(Direction direction, String expected) {
        Position position = new Position("d4");
        assertThat(position.toDirection(direction)).isEqualTo(new Position(expected));
    }

    @ParameterizedTest
    @CsvSource(value = {"a1, WEST", "a1, SOUTH", "h8, EAST", "h8, NORTH", "h8, NORTH_EAST"})
    @DisplayName("Direction에 따라서 범위를 벗어나 Position을 얻을 수 없는 경우, 자기 자신을 반환한다.")
    void toDirectionNotMove(String expected, Direction direction) {
        Position position = new Position(expected);
        assertThat(position.toDirection(direction)).isEqualTo(position);
    }
}
