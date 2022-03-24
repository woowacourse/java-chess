package chess.domain.board.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Rank;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackTurnTest {

    @DisplayName("흑팀의 차례인지 확인한다.")
    @Test
    void isBlackTurn() {
        Map<Integer, Rank> ranks = new HashMap<>();
        BlackTurn blackTurn = new BlackTurn(ranks);

        assertThat(blackTurn.isBlackTurn()).isTrue();
    }
}
