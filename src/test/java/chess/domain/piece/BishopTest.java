package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @DisplayName("색과 위치가 동일한 Bishop 인스턴스는 서로 동일하다고 간주된다.")
    @Test
    void equals_sameOnSameColorAndPosition() {
        Bishop actual = new Bishop(BLACK, Position.of("c1"));
        Bishop expected = new Bishop(BLACK, Position.of("c1"));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("다른 위치에 있는 Bishop 인스턴스는 색이 같더라도 서로 다른 것으로 간주된다.")
    @Test
    void equals_differentOnPositionDifference() {
        Bishop bishop1 = new Bishop(BLACK, Position.of("c1"));
        Bishop bishop2 = new Bishop(BLACK, Position.of("f1"));

        assertThat(bishop1).isNotEqualTo(bishop2);
    }

    @DisplayName("같은 색과 위치의 Bishop 인스턴스의 해쉬코드 값은 서로 같다.")
    @Test
    void hashCode_sameOnSameColorAndPosition() {
        int actual = new Bishop(BLACK, Position.of("c1")).hashCode();
        int expected = new Bishop(BLACK, Position.of("c1")).hashCode();

        assertThat(actual).isEqualTo(expected);
    }
}