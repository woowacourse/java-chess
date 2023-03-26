package chess;

import chess.domain.Column;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColumnTest {

    @Test
    @DisplayName("문자열 column 값을 입력할 때 해당 값을 가지는 Column 객체가 반환된다.")
    void shouldSucceedFindingColumn() {

        String columnValue = "a";

        assertThat(Column.findColumn(columnValue)).isEqualTo(Column.A);
    }

    @Test
    @DisplayName("체스판 범위에 포함된 column값이 입력됐을 때 true를 반환한다.")
    void shouldSucceedToCheckInRange() {
        char column = 'f';

        assertThat(Column.isInChessBoardRange(column)).isTrue();
    }

    @Test
    @DisplayName("체스판 범위를 벗어난 column값이 입력되었을 때 false를 반환한다.")
    void shouldSucceedToCheckOutOfRange() {
        char rank = 'i';

        assertThat(Column.isInChessBoardRange(rank)).isFalse();
    }

}