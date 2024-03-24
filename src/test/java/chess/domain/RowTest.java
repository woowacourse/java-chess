package chess.domain;

import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RowTest {

    @Test
    @DisplayName("가로는 a~h의 알파벳으로 생성된다.")
    void makeRowTest() {
        assertThatCode(() -> Row.from("a"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("a~h가 아닌 알파벳으로 만들어진 가로는 존재하지 않는다.")
    void testValueOf() {
        assertThatThrownBy(() -> Row.from("z"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("a~h까지 가능합니다.");
    }

}
