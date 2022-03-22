package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NoneTest {

    @DisplayName("위치가 동일한 인스턴스는 서로 같다.")
    @Test
    void equals_sameInstanceIfPositionIsSame() {
        None actual = new None(Position.of("a1"));
        None expected = new None(Position.of("a1"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("위치가 동일한 경우 해쉬코드의 값은 서로 같다.")
    @Test
    void hashCode_createdWithPositionAndColor() {
        int actual = new None(Position.of("a1")).hashCode();
        int expected = new None(Position.of("a1")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
