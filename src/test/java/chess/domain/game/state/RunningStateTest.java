package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.movable.Pawn;
import chess.domain.piece.movable.single.King;
import chess.domain.player.Player;
import chess.domain.player.PlayerFactory;
import chess.domain.player.Players;

class RunningStateTest {

    private RunningState gameState;

    @BeforeEach
    void setUp() {
        final Players players = Players.initialize(PlayerFactory.getInstance());
        gameState = new RunningState(players, Color.WHITE);
    }

    @DisplayName("킹이 하나 남아있다면 FinishedState 상태를 반환해야 한다.")
    @Test
    void runToFinishedState() {
        final Players players = new Players(
                Player.of(Color.WHITE, new HashMap<>(Map.of(
                        Position.from("c7"), Pawn.getWhitePawn(),
                        Position.from("d1"), King.getInstance()))),
                Player.of(Color.BLACK, new HashMap<>(Map.of(
                        Position.from("d8"), King.getInstance())))
        );

        final RunningState gameState = new RunningState(players, Color.WHITE);
        final Position source = Position.from("c7");
        final Position target = Position.from("d8");
        assertThat(gameState.move(source, target)).isInstanceOf(FinishedState.class);
    }

    @DisplayName("프로모션이 가능한 상태에서는 기물을 움직일 수 없어야 한다.")
    @Test
    void runToPromotableState() {
        final Players players = new Players(
                Player.of(Color.WHITE, new HashMap<>(Map.of(
                        Position.from("a8"), Pawn.getWhitePawn(),
                        Position.from("d1"), King.getInstance()))),
                Player.of(Color.BLACK, new HashMap<>(Map.of(
                        Position.from("d8"), King.getInstance())))
        );

        final RunningState gameState = new RunningState(players, Color.WHITE);
        assertThatThrownBy(() -> gameState.move(Position.from("a7"), Position.from("a8")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("프로모션을 진행해야 합니다.");
    }

    @DisplayName("일반적인 상황에서는 RunningState 상태를 반환해야 한다.")
    @Test
    void runToMoveState() {
        final Players players = new Players(
                Player.of(Color.WHITE, new HashMap<>(Map.of(
                        Position.from("d1"), King.getInstance()))),
                Player.of(Color.BLACK, new HashMap<>(Map.of(
                        Position.from("d8"), King.getInstance())))
        );

        final RunningState gameState = new RunningState(players, Color.WHITE);
        final Position source = Position.from("d1");
        final Position target = Position.from("d2");
        assertThat(gameState.move(source, target)).isInstanceOf(RunningState.class);
    }

    @DisplayName("실행 상태임을 나타낼 수 있어야 한다.")
    @Test
    void isRunning() {
        assertThat(gameState.isRunning()).isTrue();
    }

    @DisplayName("MoveState 상태임을 나타낼 수 있어야 한다.")
    @Test
    void isMovable() {
        assertThat(gameState.isMovable()).isTrue();
    }

    @DisplayName("PromotableState 상태임을 나타낼 수 있어야 한다.")
    @Test
    void isPromotable() {
        assertThat(gameState.isPromotable()).isFalse();
    }

}