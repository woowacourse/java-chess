package chess.piece.coordinate;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.coordinate.Row;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class RowTest {

    @ParameterizedTest
    @CsvSource(value = {"1,ONE", "2,TWO", "3,THREE", "4,FOUR", "5,FIVE", "6,SIX", "7,SEVEN", "8,EIGHT"})
    void 심볼로부터_로우값_반환(String symbol, Row row) {
        assertThat(Row.from(symbol)).isEqualTo(row);
    }

    @Test
    void 다른_로우와의_거리_반환() {
        assertThat(Row.FIVE.subtract(Row.ONE)).isEqualTo(4);
    }

    @ParameterizedTest
    @ValueSource(strings = {"TWO","SEVEN"})
    void 폰이_시작위치인지_확인(Row row) {
        assertThat(row.isPawnStartRow()).isTrue();
    }

    @Test
    void 현재_로우에서_원하는_만큼_이동() {
        assertThat(Row.TWO.up(1)).isEqualTo(Row.THREE);
    }

}
