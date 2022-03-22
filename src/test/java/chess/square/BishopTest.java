package chess.square;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.square.Color;
import chess.domain.square.Bishop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @DisplayName("같은 색의 Bishop 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColor() {
        Bishop actual = new Bishop(Color.BLACK);
        Bishop expected = new Bishop(Color.BLACK);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 색의 Bishop 인스턴스는 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnColorDifference() {
        Bishop blackBishop = new Bishop(Color.BLACK);
        Bishop whiteBishop = new Bishop(Color.WHITE);

        assertThat(blackBishop).isNotEqualTo(whiteBishop);
    }

    @DisplayName("같은 색의 Bishop 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColor() {
        int actual = new Bishop(Color.BLACK).hashCode();
        int expected = new Bishop(Color.BLACK).hashCode();

        assertThat(actual).isEqualTo(expected);
    }


}