package chess.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("현재 턴의 플레이어를 반환한다.")
    @Test
    void currentTurnOfPlayer() {
        Player first = new Player(Team.WHITE);
        Player second = new Player(Team.BLACK);
        Players players = new Players(first, second);

        assertThat(players.currentTurnOfPlayer()).isEqualTo(first);
        players.turnOver();
        assertThat(players.currentTurnOfPlayer()).isEqualTo(second);
    }
}
