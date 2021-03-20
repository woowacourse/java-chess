package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishTest {

    private static final State finish = new Finish(Color.WHITE);

    @Test
    @DisplayName("다음상태 전환 테스트")
    void nextState() {
        assertThatThrownBy(finish::nextState)
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("끝났는지 상태 테스트")
    void isFinish() {
        assertThat(finish.isFinish()).isTrue();
    }

    @Test
    @DisplayName("다음 턴으로 넘기기 테스트")
    void nextTurn() {
        assertThatCode(finish::nextTurn).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("우승자 확인 테스트")
    void winner() {
        assertThat(finish.winner()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("이동여부 테스트")
    void validateMove() {
        assertThatThrownBy(finish::validateMove)
            .isInstanceOf(IllegalArgumentException.class);
    }
}