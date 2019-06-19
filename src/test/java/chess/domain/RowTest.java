package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RowTest {
    @Test
    public void 동시성_테스트() {
        Row row1 = Row.from("1");
        Row row2 = Row.from("1");

        assertThat(row1).isEqualTo(row2);
    }

    @Test
    public void 캐싱_테스트() {
        Row row1 = Row.from("1");
        Row row2 = Row.from("1");

        assertThat(row1 == row2).isTrue();
    }

    @Test
    public void 숫자로_생성할때_테스트() {
        Row actual = Row.from("1");
        Row expected = Row.from(1);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void 범위_유효성_테스트() {
        String lessThanMin = String.valueOf(Row.MIN - 1);
        assertThrows(IllegalArgumentException.class, () -> Row.from(lessThanMin));
    }
}
