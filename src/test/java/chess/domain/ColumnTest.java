package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ColumnTest {
    @Test
    void 범위_벗어나는_값1() {
        assertThrows(InvalidColumnException.class, () ->
                Column.of("i")
        );
    }

    @Test
    void 범위_벗어나는_값2() {
        assertThrows(InvalidColumnException.class, () ->
                Column.of('3')
        );
    }

    @Test
    void null_확인() {
        assertThrows(InvalidColumnException.class, () ->
                Column.of(null)
        );
    }

    @Test
    void column_객체_캐싱_확인() {
        assertThat(Column.of('a') == Column.of("a")).isTrue();
    }
}