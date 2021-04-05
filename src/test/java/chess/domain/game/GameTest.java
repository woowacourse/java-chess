package chess.domain.game;

import chess.domain.Player;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    @DisplayName("게임에서 기물이동을 잘 하는지 확인")
    void pickEndPiece() {
        assertThatCode(() -> game.move(Position.from("a7"), Position.from("a6")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("킹이 두 개 일 때 isNotEnd 실행 시 true를 반환하는지 확인")
    void isNotEnd() {
        assertTrue(game.isNotEnd());
    }

    @Test
    @DisplayName("현재 플레이어의 컬러를 잘 반환하는지 확인")
    void currentPlayer() {
        Player player = game.currentPlayer();
        assertThat(player.getColor()).isEqualTo(Color.BLACK);
    }
}