package chess.domain.position;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Column;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ColumnTest {

    @Test
    @DisplayName("문자열로 Column 상수를 얻는다.")
    void ofByString() {
        Column column = Column.of("a");

        assertThat(column).isEqualTo(Column.A);
    }

    @Test
    @DisplayName("지정되지 않은 문자열로는 Column 상수를 얻을 수 없다.")
    void ofByStringThrowException() {
        assertThatThrownBy(() -> Column.of("i"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 열 이름이 들어왔습니다.");
    }

    @Test
    @DisplayName("인덱스로 Column 상수를 얻는다.")
    void ofByInt() {
        Column column = Column.of(1);
        assertThat(column).isEqualTo(Column.A);
    }

    @Test
    @DisplayName("범위를 벗어난 인덱스로는 Column 상수를 얻을 수 없다.")
    void ofByIntThrowException() {
        assertThatThrownBy(() -> Column.of(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 인덱스의 열은 존재하지 않습니다.");
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"-1,C", "1,E"})
    @DisplayName("이동 후 Column을 반환한다.")
    void move(int moveValue, Column expected) {
        Column move = Column.D.move(moveValue);
        Assertions.assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"A,-1", "H,1"})
    @DisplayName("이동할 수 없는 Column일 경우, 예외를 발생한다.")
    void moveThrowException(Column column, int moveValue) {
        assertThatThrownBy(() -> column.move(moveValue))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("범위를 벗어났습니다.");
    }

    @Test
    @DisplayName("두 컬럼 사이의 value 차이를 구한다.")
    void calculateDifference() {
        Column source = Column.of(5);
        Column target = Column.of(3);
        assertThat(Column.calculateDifference(source, target)).isEqualTo(2);
    }
}
