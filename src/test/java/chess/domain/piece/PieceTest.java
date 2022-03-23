package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("입력된 두개의 문자가 같은 케이스면 참을 반환한다.")
    @Test
    void verifySameCase_True() {
        Knight knight = Knight.createBlack(new Position("b8"));

        assertThat(knight.isEnemy("n")).isTrue();
    }

    @DisplayName("입력된 두개의 문자가 다른 케이스면 거짓을 반환한다.")
    @Test
    void verifySameCase_False() {
        Knight knight = Knight.createBlack(new Position("b8"));

        assertThat(knight.isEnemy("K")).isFalse();
    }
}
