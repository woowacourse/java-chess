package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RowTest {

    @Test
    @DisplayName("문자열로 Row 상수를 얻는다.")
    void of() {
        final Row row = Row.of(1);

        assertThat(row).isEqualTo(Row.ONE);
    }

    @Test
    @DisplayName("지정되지 않은 문자열로는 Row 상수를 얻을 수 없다.")
    void ofThrowException() {
        assertThatThrownBy(() ->
                Row.of(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 행 이름이 들어왔습니다.");
    }

    @Test
    @DisplayName("인덱스로 Row 상수를 얻는다.")
    void ofByInt() {
        final Row row = Row.of(1);

        assertThat(row).isEqualTo(Row.ONE);
    }

    @Test
    @DisplayName("범위를 벗어난 인덱스로는 Row 상수를 얻을 수 없다.")
    void ofByIntThrowException() {
        assertThatThrownBy(() ->
                Row.of(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 행 이름이 들어왔습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"-1,FOUR", "1,SIX"})
    @DisplayName("이동 후 Row를 반환한다.")
    void move(final int moveValue, final Row expected) {
        final Row move = Row.FIVE.move(moveValue);

        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE,-1", "EIGHT,1"})
    @DisplayName("이동할 수 없는 Row일 경우, 예외를 발생한다.")
    void moveThrowException(final Row row, final int moveValue) {
        assertThatThrownBy(() ->
                row.move(moveValue))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("범위를 벗어났습니다.");
    }
}
