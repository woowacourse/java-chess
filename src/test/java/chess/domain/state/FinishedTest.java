package chess.domain.state;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {

    @DisplayName("BlackWin은 끝난 상태이다")
    @Test
    void testBlackWinIsFinished() {
        State blackWin = new BlackWin();
        Assertions.assertThat(blackWin.isFinished()).isTrue();
    }

    @DisplayName("WhiteWin은 끝난 상태이다")
    @Test
    void testWhiteWinIsFinished() {
        State whiteWin = new WhiteWin();
        Assertions.assertThat(whiteWin.isFinished()).isTrue();
    }
}
