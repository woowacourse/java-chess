package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlankTest {

    @Test
    @DisplayName("Blank 인지 확인")
    void isBlank() {
        Blank blank = new Blank();
        assertThat(blank.isBlank()).isTrue();
    }

    @Test
    @DisplayName("이동 전략을 가져올 시 에러가 발생한다.")
    void getMoveStrategyException() {
        Blank blank = new Blank();
        assertThatThrownBy(blank::getMoveStrategy)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 이동전략을 가져올 수 없는 기물입니다.");
    }

    @Test
    @DisplayName("King이 아니다.")
    void isNotKing() {
        Blank blank = new Blank();
        assertThat(blank.isKing()).isFalse();
    }
}