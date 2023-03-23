package chess.domain.pieces;

import chess.domain.pieces.component.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("Name은 같은이름의 인자를 가지면 같다고 판단한다.")
    void test_() {
        // given & when
        Name name1 = new Name("채채");
        Name name2 = new Name("채채");

        // then
        Assertions.assertThat(name1.equals(name2)).isTrue();
    }
}
