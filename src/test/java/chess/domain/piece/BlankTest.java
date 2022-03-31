package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlankTest {

    private Blank blank = new Blank();

    @Test
    @DisplayName("Blank 인지 확인")
    void isBlank() {
        assertThat(blank.isBlank()).isTrue();
    }

    @Test
    @DisplayName("King 인지 확인")
    void isKing() {
        assertThat(blank.isKing()).isFalse();
    }

    @Test
    @DisplayName("Pawn 인지 확인")
    void isPawn() {
        assertThat(blank.isPawn()).isFalse();
    }

    @Test
    @DisplayName("Black 는 이동전략이 없다.")
    void getMoveStrategy() {
        assertThatThrownBy(() -> blank.getMoveStrategy())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 이동전략을 가져올 수 없는 기물입니다.");
    }
}