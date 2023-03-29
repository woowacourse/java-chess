package chess.domain.position;

import static chess.domain.position.Column.INVALID_RANGE_OF_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ColumnTest {

    @ParameterizedTest(name = "열은 최소 0, 최대 7을 가질 수 있다. 입력값: {0}")
    @ValueSource(ints = {-1 ,8})
    void 열은_최소_0_최대_7을_가질_수_있다_실패(int column) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Column(column)
        ).withMessage(INVALID_RANGE_OF_VALUE);
    }

    @ParameterizedTest(name = "열은 최소 0, 최대 7을 가질 수 있다. 입력값: {0}")
    @ValueSource(ints = {0 ,7})
    void 열은_최소_0_최대_7을_가질_수_있다_성공(int column) {
        assertThatNoException().isThrownBy(
                () -> new Column(column)
        );
    }

    @Test
    @DisplayName("논리적으로 같은 객체이다.")
    void 논리적으로_같은_객체이다() {
        Column column = new Column(1);
        Column otherColumn = new Column(1);

        assertThat(column.equals(otherColumn)).isTrue();
    }

    @Test
    @DisplayName("값을 받아 더할 수 있다")
    void 값을_받아_더할_수_있다() {
        Column column = new Column(0);
        Column newColumn = column.add(1);

        assertThat(newColumn.getColumn()).isEqualTo(1);
    }
}
