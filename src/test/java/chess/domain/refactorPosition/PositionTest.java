package chess.domain.refactorPosition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {

    @ParameterizedTest
    @CsvSource(value = {"a,1"})
    @DisplayName("범위 내의 행과 열은 위치를 생성할 수 있다.")
    void createPosition(String column, String row) {
        final Position position = Position.of(column, row);

        assertDoesNotThrow(() -> position);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,0", "a,9", "i,1", "0,1", "a,a"})
    @DisplayName("범위 외의 행과 열은 위치를 생성할 수 없다.")
    void createFormatInvalidPosition(String column, String row) {
        assertThatThrownBy(() -> Position.of(column, row))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,1,0,1,true", "a,1,1,0,true", "h,1,1,0,false", "a,8,0,1,false"})
    @DisplayName("위치를 한 칸 전진할 수 있는지 확인 할 수 있다.")
    void isMovablePosition(String fromColumn, String fromRow, int column, int row, boolean flag) {
        final Position fromPosition = Position.of(fromColumn, fromRow);

        assertThat(fromPosition.isMovable(column, row)).isEqualTo(flag);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,2,0,1", "b,2,1,1"})
    @DisplayName("위치를 한 칸 전진시킬 수 있다.")
    void movePosition(String toColumn, String toRow, int column, int row) {
        final Position fromPosition = Position.of("a", "1");
        final Position toPosition = Position.of(toColumn, toRow);

        assertThat(fromPosition.move(column, row)).isEqualTo(toPosition);
    }
}
