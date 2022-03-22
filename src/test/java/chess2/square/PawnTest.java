package chess2.square;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess2.domain.square.Color;
import chess2.domain.square.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {

    @DisplayName("같은 색의 Pawn 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColor() {
        Pawn actual = new Pawn(Color.BLACK);
        Pawn expected = new Pawn(Color.BLACK);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 색의 Pawn 인스턴스는 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnColorDifference() {
        Pawn blackPawn = new Pawn(Color.BLACK);
        Pawn whitePawn = new Pawn(Color.WHITE);

        assertThat(blackPawn).isNotEqualTo(whitePawn);
    }

    @DisplayName("같은 색의 Pawn 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColor() {
        int actual = new Pawn(Color.BLACK).hashCode();
        int expected = new Pawn(Color.BLACK).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
