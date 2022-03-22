package chess.square;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.square.Color;
import chess.domain.square.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {

    @DisplayName("같은 색의 Rook 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColor() {
        Rook actual = new Rook(Color.BLACK);
        Rook expected = new Rook(Color.BLACK);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 색의 Rook 인스턴스는 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnColorDifference() {
        Rook blackRook = new Rook(Color.BLACK);
        Rook whiteRook = new Rook(Color.WHITE);

        assertThat(blackRook).isNotEqualTo(whiteRook);
    }

    @DisplayName("같은 색의 Rook 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColor() {
        int actual = new Rook(Color.BLACK).hashCode();
        int expected = new Rook(Color.BLACK).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
