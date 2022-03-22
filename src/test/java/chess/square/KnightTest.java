package chess.square;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.square.Color;
import chess.domain.square.Knight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @DisplayName("같은 색의 Knight 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColor() {
        Knight actual = new Knight(Color.BLACK);
        Knight expected = new Knight(Color.BLACK);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 색의 Knight 인스턴스는 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnColorDifference() {
        Knight blackKnight = new Knight(Color.BLACK);
        Knight whiteKnight = new Knight(Color.WHITE);

        assertThat(blackKnight).isNotEqualTo(whiteKnight);
    }

    @DisplayName("같은 색의 Knight 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColor() {
        int actual = new Knight(Color.BLACK).hashCode();
        int expected = new Knight(Color.BLACK).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
