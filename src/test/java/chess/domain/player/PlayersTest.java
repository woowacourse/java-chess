package chess.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.movable.Pawn;
import chess.domain.piece.movable.multiple.Queen;

class PlayersTest {

    private Players players;

    @BeforeEach
    void setUp() {
        players = new Players(List.of(
                new Player(Color.WHITE, new HashMap<>(Map.of(
                        Position.from("a2"), Pawn.getWhitePawn(),
                        Position.from("d1"), Queen.getInstance()
                ))),
                new Player(Color.BLACK, new HashMap<>(Map.of(
                        Position.from("a7"), Pawn.getBlackPawn(),
                        Position.from("b3"), Pawn.getBlackPawn(),
                        Position.from("d2"), Pawn.getBlackPawn())
                )))
        );
    }

    @DisplayName("플레이어는 다른 플레이어의 기물을 움직일 수 없어야 한다.")
    @Test
    void movePieceOfOtherPlayerException() {
        final Position source = Position.from("a7");
        final Position target = Position.from("a5");
        assertThatThrownBy(() -> players.move(Color.WHITE, source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다른 플레이어의 기물은 움직일 수 없습니다.");
    }

    @DisplayName("플레이어는 목적지로의 경로를 기물이 하나라도 가로막고 있으면 목적지로 접근할 수 없어야 한다.")
    @Test
    void movableRouteBlockedException() {
        final Position source = Position.from("d1");
        final Position target = Position.from("d8");
        assertThatThrownBy(() -> players.move(Color.WHITE, source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("목적지까지의 경로를 다른 기물이 가로막고 있습니다.");
    }

    @DisplayName("플레이어는 출발지에 위치한 본인의 기물을 비어있는 목적지로 이동시킬 수 있어야 한다.")
    @Test
    void movePieceToEmptyPosition() {
        final Position source = Position.from("a2");
        final Position target = Position.from("a4");
        players.move(Color.WHITE, source, target);

        final Map<Position, Piece> playerPieces = players.getPiecesByPlayer(Color.WHITE);
        assertAll(() -> {
            assertThat(playerPieces).doesNotContainKey(source);
            assertThat(playerPieces).containsKey(target);
        });
    }

    @DisplayName("플레이어는 출발지에 위치한 본인의 기물을 타인의 기물이 위치한 목적지로 공격할 수 있어야 한다.")
    @Test
    void attackEnemyPiece() {
        final Position source = Position.from("a2");
        final Position target = Position.from("b3");
        players.move(Color.WHITE, source, target);

        final Map<Position, Piece> playerPieces = players.getPiecesByPlayer(Color.WHITE);
        final Map<Position, Piece> enemyPieces = players.getPiecesByPlayer(Color.BLACK);
        assertAll(() -> {
            assertThat(playerPieces).doesNotContainKey(source);
            assertThat(playerPieces).containsKey(target);
            assertThat(enemyPieces).doesNotContainKey(target);
        });
    }
}
