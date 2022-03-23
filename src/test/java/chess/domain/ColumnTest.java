package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColumnTest {

    @Test
    @DisplayName("문자열로 Column 상수를 얻는다.")
    void of() {
        Column column = Column.of("a");

        assertThat(column).isEqualTo(Column.A);
    }

    @Test
    @DisplayName("지정되지 않은 문자열로는 Column 상수를 얻을 수 없다.")
    void ofThrowException() {

        assertThatThrownBy(() -> Column.of("i"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 열 이름이 들어왔습니다.");
    }
}
