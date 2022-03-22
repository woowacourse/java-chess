package chess.square;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.square.Color;
import chess.domain.square.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @DisplayName("같은 색의 Queen 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColor() {
        Queen actual = new Queen(Color.BLACK);
        Queen expected = new Queen(Color.BLACK);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 색의 Queen 인스턴스는 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnColorDifference() {
        Queen blackQueen = new Queen(Color.BLACK);
        Queen whiteQueen = new Queen(Color.WHITE);

        assertThat(blackQueen).isNotEqualTo(whiteQueen);
    }

    @DisplayName("같은 색의 Queen 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColor() {
        int actual = new Queen(Color.BLACK).hashCode();
        int expected = new Queen(Color.BLACK).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
