package chess.domain.position;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RowTest {

    @Test
    @DisplayName("문자열로 Row 상수를 얻는다.")
    void of() {
        Row row = Row.of(1);

        assertThat(row).isEqualTo(Row.ONE);
    }

    @Test
    @DisplayName("지정되지 않은 문자열로는 Row 상수를 얻을 수 없다.")
    void ofThrowException() {
        assertThatThrownBy(() -> Row.of(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 행 이름이 들어왔습니다.");
    }

    @Test
    @DisplayName("인덱스로 Row 상수를 얻는다.")
    void ofByInt() {
        Row row = Row.of(1);
        assertThat(row).isEqualTo(Row.ONE);
    }

    @Test
    @DisplayName("범위를 벗어난 인덱스로는 Row 상수를 얻을 수 없다.")
    void ofByIntThrowException() {
        assertThatThrownBy(() -> Row.of(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 행 이름이 들어왔습니다.");
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"-1,FOUR", "1,SIX"})
    @DisplayName("이동 후 Row를 반환한다.")
    void move(int moveValue, Row expected) {
        Row move = Row.FIVE.move(moveValue);
        Assertions.assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"ONE,-1", "EIGHT,1"})
    @DisplayName("이동할 수 없는 Row일 경우, 예외를 발생한다.")
    void moveThrowException(Row row, int moveValue) {
        assertThatThrownBy(() -> row.move(moveValue))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("범위를 벗어났습니다.");
    }

    @Test
    @DisplayName("순서를 뒤집은 리스트를 반환한다.")
    void getRowsReverseOrder() {
        List<Row> rowsReverseOrder = Row.getRowsReverseOrder();
        assertThat(rowsReverseOrder).containsExactly(
                Row.EIGHT, Row.SEVEN, Row.SIX, Row.FIVE,
                Row.FOUR, Row.THREE, Row.TWO, Row.ONE
        );
    }

    @Test
    @DisplayName("두 로우 사이의 value 차이를 구한다.")
    void calculateDifference() {
        Row source = Row.of(7);
        Row target = Row.of(3);
        assertThat(Row.calculateDifference(source, target)).isEqualTo(4);
    }
}
