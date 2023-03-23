package chess.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.Camp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTest {

    @Test
    @DisplayName("findNextPlayer()는 호출하면 다음 차례를 진행할 플레이어의 진영을 반환한다.")
    void findNextPlayer_whenCall_thenReturnNextPlayer() {
        final Turn turn = new Turn();

        final Camp whiteCamp = turn.findNextPlayer();
        assertThat(whiteCamp).isSameAs(Camp.WHITE);

        final Camp blackCamp = turn.findNextPlayer();
        assertThat(blackCamp).isSameAs(Camp.BLACK);
    }
}
