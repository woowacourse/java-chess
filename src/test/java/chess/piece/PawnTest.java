package chess.piece;

import static chess.domain.piece.Color.BLACK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Pawn;
import chess.domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @DisplayName("색과 위치가 동일한 Pawn 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColorAndPosition() {
        Pawn actual = new Pawn(BLACK, Position.of("a2"));
        Pawn expected = new Pawn(BLACK, Position.of("a2"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 위치에 있는 Pawn 인스턴스는 색이 같더라도 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnPositionDifference() {
        Pawn pawn1 = new Pawn(BLACK, Position.of("a2"));
        Pawn pawn2 = new Pawn(BLACK, Position.of("b2"));

        assertThat(pawn1).isNotEqualTo(pawn2);
    }

    @DisplayName("같은 색과 위치의 Pawn 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColor() {
        int actual = new Pawn(BLACK, Position.of("a2")).hashCode();
        int expected = new Pawn(BLACK, Position.of("a2")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
