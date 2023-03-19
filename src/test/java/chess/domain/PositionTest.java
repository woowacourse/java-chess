package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PositionTest {
    @DisplayName("생성한다.")
    @Test
    void create() {
        // expect
        assertThatNoException().isThrownBy(() -> Position.of(1, 1));
    }

    @DisplayName("입력받은 문자열로 부터 Position 값을 생성한다.")
    @CsvSource(value = {"a1:0:7", "b1:1:7", "d4:3:4"}, delimiter = ':')
    @ParameterizedTest
    void from(final String rowColumn, final int row, final int column) {
        // given & when
        final Position findPosition = Position.from(rowColumn);

        // then
        assertThat(findPosition)
                .hasFieldOrPropertyWithValue("row", row)
                .hasFieldOrPropertyWithValue("column", column);
    }

    @DisplayName("논리적으로 같을 때 동등한지 확인한다.")
    @CsvSource(value = {"0:0:0:0", "4:2:4:2", "1:3:1:3", "13:19:13:19"}, delimiter = ':')
    @ParameterizedTest
    void equals(final int rowOne, final int columnOne, final int rowTwo, final int columnTwo) {
        // given & when
        final Position positionOne = Position.of(rowOne, columnOne);
        final Position positionTwo = Position.of(rowTwo, columnTwo);

        // then
        assertThat(positionOne).isEqualTo(positionTwo);
    }

    @DisplayName("column 내림차순 -> row 내림차순으로 비교되는지 확인한다.")
    @CsvSource(value = {"0:0:0:0:0", "4:2:1:0:1", "1:3:5:3:-1", "13:3:13:19:-1"}, delimiter = ':')
    @ParameterizedTest
    void compareTo(final int rowOne, final int columnOne, final int rowTwo, final int columnTwo, final int expectedCompare) {
        // given
        final Position positionOne = Position.of(rowOne, columnOne);
        final Position positionTwo = Position.of(rowTwo, columnTwo);

        // when
        final int compareResult = positionOne.compareTo(positionTwo);

        // then
        assertThat(compareResult).isEqualTo(expectedCompare);
    }

    @DisplayName("움직이다.")
    @CsvSource(value = {"1:0:5:2:6:2", "4:2:1:2:5:4", "1:3:5:7:6:10", "3:3:5:2:8:5"}, delimiter = ':')
    @ParameterizedTest
    void move(
            final int rowOne, final int columnOne,
            final int rowTwo, final int columnTwo,
            final int movedRow, final int movedColumn
    ) {
        // given
        final Position position = Position.of(rowOne, columnOne);
        final Position movePosition = Position.of(rowTwo, columnTwo);
        final Position expectedPosition = Position.of(movedRow, movedColumn);

        // when
        final Position movedPosition = position.move(movePosition);

        // then
        assertThat(movedPosition).isEqualTo(expectedPosition);
    }
}
