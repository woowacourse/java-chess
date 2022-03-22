package chess.square;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.square.Color;
import chess.domain.square.King;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @DisplayName("같은 색의 King 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColor() {
        King actual = new King(Color.BLACK);
        King expected = new King(Color.BLACK);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 색의 King 인스턴스는 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnColorDifference() {
        King blackKing = new King(Color.BLACK);
        King whiteKing = new King(Color.WHITE);

        assertThat(blackKing).isNotEqualTo(whiteKing);
    }

    @DisplayName("같은 색의 King 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColor() {
        int actual = new King(Color.BLACK).hashCode();
        int expected = new King(Color.BLACK).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}
