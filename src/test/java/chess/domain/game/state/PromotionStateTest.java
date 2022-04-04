package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.movable.Pawn;
import chess.domain.player.Player;
import chess.domain.player.Players;

class PromotionStateTest {

    private PromotionState gameState;

    @BeforeEach
    void setUp() {
        final Players players = new Players(Player.of(
                Color.WHITE, new HashMap<>(Map.of(Position.from("a8"), Pawn.getWhitePawn()))));
        gameState = new PromotionState(players, Color.WHITE);
    }

    @DisplayName("프로모션 수행 이후 MoveState 상태를 반환해야 한다.")
    @Test
    void runToMoveState() {
        assertThat(gameState.run("Queen")).isInstanceOf(MoveState.class);
    }

    @DisplayName("실행 상태임을 나타낼 수 있어야 한다.")
    @Test
    void isRunning() {
        assertThat(gameState.isRunning()).isTrue();
    }

    @DisplayName("MoveState 상태임을 나타낼 수 있어야 한다.")
    @Test
    void isMovable() {
        assertThat(gameState.isMovable()).isFalse();
    }

    @DisplayName("PromotableState 상태임을 나타낼 수 있어야 한다.")
    @Test
    void isPromotable() {
        assertThat(gameState.isPromotable()).isTrue();
    }

}