package chess.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.movable.Pawn;
import chess.domain.piece.movable.multiple.Queen;
import chess.domain.piece.movable.single.King;

class PlayersTest {

    private Players players;

    @BeforeEach
    void setUp() {
        players = new Players(
                Player.of(Color.WHITE, new HashMap<>(Map.of(
                        Position.from("a2"), Pawn.getWhitePawn(),
                        Position.from("d1"), Queen.getInstance()
                ))),
                Player.of(Color.BLACK, new HashMap<>(Map.of(
                        Position.from("a7"), Pawn.getBlackPawn(),
                        Position.from("b3"), Pawn.getBlackPawn(),
                        Position.from("d2"), Pawn.getBlackPawn())
                ))
        );
    }

    @DisplayName("플레이어의 색상은 중복될 수 없어야 한다.")
    @Test
    void playerColorDuplicatedException() {
        final Player player1 = Player.of(Color.WHITE, Collections.emptyMap());
        final Player player2 = Player.of(Color.WHITE, Collections.emptyMap());

        assertThatThrownBy(() -> new Players(player1, player2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어의 색상이 중복됩니다.");
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

    @DisplayName("특정 플레이어가 프로모션 가능한 폰을 지니고 있는지 확인할 수 있어야 한다.")
    @ParameterizedTest
    @CsvSource(value = {"a7,false", "a8,true"})
    void isPlayerAbleToPromotePawn(final String position, final boolean expected) {
        final Players players = new Players(
                Player.of(Color.WHITE, new HashMap<>(Map.of(Position.from(position), Pawn.getWhitePawn()))));
        final boolean actual = players.isPlayerAbleToPromotePawn(Color.WHITE);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("특정 플레이어의 프로모션을 수행할 수 있어야 한다.")
    @Test
    void promotePawn() {
        final Position position = Position.from("a8");
        final Players players = new Players(
                Player.of(Color.WHITE, new HashMap<>(Map.of(position, Pawn.getWhitePawn()))));
        players.promotePawn(Color.WHITE, "Queen");

        final Map<Position, Piece> playerPieces = players.getPiecesByPlayer(Color.WHITE);
        assertThat(playerPieces.get(position)).isInstanceOf(Queen.class);
    }

    @DisplayName("킹이 살아있는 플레이어가 한명만 남았는지 확인할 수 있어야 한다.")
    @ParameterizedTest
    @MethodSource("provideForIsOnlyOneKingLeft")
    void isOnlyOneKingLeft(final Piece piece, final boolean expected) {
        final Players players = new Players(
                Player.of(Color.WHITE, Map.of(Position.from("a1"), King.getInstance())),
                Player.of(Color.BLACK, Map.of(Position.from("a8"), piece)));
        final boolean actual = players.isOnlyOneKingLeft();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideForIsOnlyOneKingLeft() {
        return Stream.of(
                Arguments.of(Queen.getInstance(), true),
                Arguments.of(King.getInstance(), false)
        );
    }
}
