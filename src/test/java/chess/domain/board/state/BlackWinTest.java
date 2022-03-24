package chess.domain.board.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Rank;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackWinTest {

    @DisplayName("흑팀의 승리인지 확인한다.")
    @Test
    void isBlackWin() {
        Map<Integer, Rank> ranks = new HashMap<>();
        BlackWin blackWin = new BlackWin(ranks);

        assertThat(blackWin.isBlackWin()).isTrue();
    }
}
