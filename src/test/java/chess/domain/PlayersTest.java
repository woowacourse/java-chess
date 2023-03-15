package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("white 플레이어와 black 플레이어의 모든 position 반환한다.")
    void getAllPosition() {
        // given
        Pieces whitePieces = Pieces.createWhitePieces();
        Pieces blackPieces = Pieces.createBlackPieces(whitePieces);
        Players players = Players.from(Player.fromWhitePlayer(whitePieces), Player.fromBlackPlayer(blackPieces));

        // when, then
        Assertions.assertThat(players.getAllPosition()).hasSize(32);
    }
}
