package chess.domain.board.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Rank;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteWinTest {

    @DisplayName("흑팀의 승리가 아닌지 확인한다.")
    @Test
    void isBlackWin() {
        Map<Integer, Rank> ranks = new HashMap<>();
        WhiteWin whiteWin = new WhiteWin(ranks);

        assertThat(whiteWin.isBlackWin()).isFalse();
    }
}
