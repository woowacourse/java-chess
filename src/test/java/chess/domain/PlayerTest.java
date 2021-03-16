package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.type.PlayerType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("백과 흑 플레이어를 생성한다.")
    @Test
    void createPlayers() {
        Player whitePlayer = new Player(PlayerType.WHITE);
        Player blackPlayer = new Player(PlayerType.BLACK);
    }
}