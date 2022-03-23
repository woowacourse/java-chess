package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RowTest {

    @Test
    @DisplayName("문자열로 Row 상수를 얻는다.")
    void of() {
        Row row = Row.of("1");

        assertThat(row).isEqualTo(Row.ONE);
    }

    @Test
    @DisplayName("지정되지 않은 문자열로는 Row 상수를 얻을 수 없다.")
    void ofThrowException() {

        assertThatThrownBy(() -> Row.of("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 행 이름이 들어왔습니다.");
    }
}
