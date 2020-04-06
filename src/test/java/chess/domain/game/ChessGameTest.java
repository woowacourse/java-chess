package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.game.state.Ready;

public class ChessGameTest {
    @Test
    @DisplayName("체스 게임 생성")
    void constructor() {
        assertThat(new ChessGame(new Ready())).isInstanceOf(ChessGame.class);
    }
}
