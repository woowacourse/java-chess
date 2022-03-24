package chess.domain.board.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Rank;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteTurnTest {

    @DisplayName("흑팀의 차례가 아닌지 확인한다.")
    @Test
    void isNotBlackTurn() {
        Map<Integer, Rank> ranks = new HashMap<>();
        WhiteTurn whiteTurn = new WhiteTurn(ranks);

        assertThat(whiteTurn.isBlackTurn()).isFalse();
    }
}
