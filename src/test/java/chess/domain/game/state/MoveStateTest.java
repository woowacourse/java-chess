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
import chess.domain.piece.movable.single.King;
import chess.domain.player.Player;
import chess.domain.player.PlayerFactory;
import chess.domain.player.Players;

class MoveStateTest {

    private MoveState gameState;

    @BeforeEach
    void setUp() {
        final Players players = Players.initialize(PlayerFactory.getInstance());
        gameState = new MoveState(players, Color.WHITE);
    }

    @DisplayName("킹이 하나 남아있다면 FinishedState 상태를 반환해야 한다.")
    @Test
    void runToFinishedState() {
        final Players players = new Players(
                new Player(Color.WHITE, new HashMap<>(Map.of(
                        Position.from("c7"), Pawn.getWhitePawn(),
                        Position.from("d1"), King.getInstance()))),
                new Player(Color.BLACK, new HashMap<>(Map.of(
                        Position.from("d8"), King.getInstance())))
        );

        final MoveState gameState = new MoveState(players, Color.WHITE);
        final Position source = Position.from("c7");
        final Position target = Position.from("d8");
        assertThat(gameState.run(source, target)).isInstanceOf(FinishedState.class);
    }

    @DisplayName("프로모션이 가능하다면 PromotionState 상태를 반환해야 한다.")
    @Test
    void runToPromotableState() {
        final Players players = new Players(
                new Player(Color.WHITE, new HashMap<>(Map.of(
                        Position.from("a7"), Pawn.getWhitePawn(),
                        Position.from("d1"), King.getInstance()))),
                new Player(Color.BLACK, new HashMap<>(Map.of(
                        Position.from("d8"), King.getInstance())))
        );

        final MoveState gameState = new MoveState(players, Color.WHITE);
        final Position source = Position.from("a7");
        final Position target = Position.from("a8");
        assertThat(gameState.run(source, target)).isInstanceOf(PromotionState.class);
    }

    @DisplayName("일반적인 상황에서는 MoveState 상태를 반환해야 한다.")
    @Test
    void runToMoveState() {
        final Players players = new Players(
                new Player(Color.WHITE, new HashMap<>(Map.of(
                        Position.from("d1"), King.getInstance()))),
                new Player(Color.BLACK, new HashMap<>(Map.of(
                        Position.from("d8"), King.getInstance())))
        );

        final MoveState gameState = new MoveState(players, Color.WHITE);
        final Position source = Position.from("d1");
        final Position target = Position.from("d2");
        assertThat(gameState.run(source, target)).isInstanceOf(MoveState.class);
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