package chess.piece.coordinate;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.coordinate.Column;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ColumnTest {

    @ParameterizedTest
    @CsvSource(value = {"a,A","b,B","c,C","d,D","e,E","f,F","g,G","h,H"})
    void 문자로부터_컬럼_반환(String column, Column expected) {
        assertThat(Column.fromName(column)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,a","2,b","3,c","4,d","5,e","6,f","7,g","8,h"})
    void 인덱스로부터_심볼_반환(int index, String name) {
        assertThat(Column.symbolFromIndex(index)).isEqualTo(name);
    }

    @Test
    void 다른_컬럼과의_거리_반환() {
        assertThat(Column.C.subtract(Column.B)).isEqualTo(1);
    }

    @Test
    void 현재_컬럼에서_원하는_만큼_이동() {
        assertThat(Column.C.up(1)).isEqualTo(Column.D);
    }
}
