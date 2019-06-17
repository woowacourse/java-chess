package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ColumnTest {

    @Test
    public void 동치성_테스트() {
        Column column1 = Column.from("a");
        Column column2 = Column.from("a");

        assertThat(column1).isEqualTo(column2);
    }

    @Test
    public void 캐싱_테스트() {
        Column column1 = Column.from("a");
        Column column2 = Column.from("a");

        assertThat(column1 == column2).isTrue();
    }

    @Test
    public void 범위_유효성_테스트() {
        String overThanMax = String.valueOf(Column.MAX + 1);
        assertThrows(IllegalArgumentException.class, () -> Column.from(overThanMax));
    }

}