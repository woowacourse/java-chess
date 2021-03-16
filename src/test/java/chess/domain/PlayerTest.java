package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.player.Player;
import chess.domain.player.PlayerType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PlayerTest {
    @DisplayName("백과 흑 플레이어를 생성한다.")
    @EnumSource(PlayerType.class)
    @ParameterizedTest
    void createPlayers(PlayerType playerType) {
        assertThatCode(() -> new Player(playerType))
            .doesNotThrowAnyException();
    }
}