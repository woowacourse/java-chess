package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @DisplayName("폰을 생성한다.")
    @Test
    void init() {
        assertThatCode(() -> new Pawn(Position.of("a1"), Color.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("위치와 색이 동일한 폰 인스턴스는 서로 같다.")
    @Test
    void equals_sameInstanceIfPositionAndColorIsSame() {
        Pawn actual = new Pawn(Position.of("a1"), Color.WHITE);
        Pawn expected = new Pawn(Position.of("a1"), Color.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("위치와 색이 동일한 폰의 해쉬코드는 서로 같다.")
    @Test
    void hashCode_createdWithPositionAndColor() {
        int actual = new Pawn(Position.of("a1"), Color.WHITE).hashCode();
        int expected = new Pawn(Position.of("a1"), Color.WHITE).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
