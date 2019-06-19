package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RowTest {
    @Test
    void 범위_벗어나는_값1() {
        assertThrows(InvalidRowException.class, () ->
                Row.of("0")
        );
    }

    @Test
    void 범위_벗어나는_값2() {
        assertThrows(InvalidRowException.class, () ->
                Row.of("9")
        );
    }

    @Test
    void 범위_벗어나는_값3() {
        assertThrows(InvalidRowException.class, () ->
                Row.of("a")
        );
    }

    @Test
    void null_확인() {
        assertThrows(InvalidRowException.class, () ->
                Row.of(null)
        );
    }

    @Test
    void column_객체_캐싱_확인() {
        assertThat(Row.of("1") == Row.of(1)).isTrue();
    }
}