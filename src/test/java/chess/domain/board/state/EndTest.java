package chess.domain.board.state;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @DisplayName("게임이 종료되었는지 확인한다.")
    @Test
    void isEnd() {
        BlackWin blackWin = new BlackWin(new HashMap<>());
        WhiteWin whiteWin = new WhiteWin(new HashMap<>());

        assertThat(blackWin.isEnd()).isTrue();
        assertThat(whiteWin.isEnd()).isTrue();
    }


}
