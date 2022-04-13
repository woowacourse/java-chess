package chess.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
import chess.domain.piece.movable.multiple.Rook;
import chess.domain.piece.movable.single.King;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = Player.of(Color.WHITE, new HashMap<>(Map.of(
                Position.from("a1"), Rook.getInstance(),
                Position.from("a2"), Pawn.getWhitePawn(),
                Position.from("d1"), Queen.getInstance()
        )));
    }

    @DisplayName("보유한 기물 중 하나는 출발지에 위치해야만 한다.")
    @Test
    void noSuchPiecePlacedAtPosition() {
        assertThatThrownBy(() -> player.calculateRouteToMove(Position.from("a8"), Position.from("a7")))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("위치에 해당하는 기물을 찾을 수 없습니다.");
    }

    @DisplayName("보유한 기물로부터 출발지에서 목적지까지의 이동경로를 계산할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}, 이동경로 : {2}")
    @MethodSource("provideForCalculateRouteToMove")
    void calculateRouteToMove(final Position source, final Position target, final List<Position> expectedRoute) {
        final List<Position> actualRoute = player.calculateRouteToMove(source, target);
        assertThat(actualRoute).isEqualTo(expectedRoute);
    }

    private static Stream<Arguments> provideForCalculateRouteToMove() {
        return Stream.of(
                Arguments.of(
                        Position.from("a2"),
                        Position.from("a4"),
                        List.of(Position.from("a3"), Position.from("a4"))
                ),
                Arguments.of(
                        Position.from("d1"),
                        Position.from("d4"),
                        List.of(Position.from("d2"), Position.from("d3"), Position.from("d4"))
                )
        );
    }

    @DisplayName("보유한 기물로부터 출발지에서 목적지까지의 공격경로를 계산할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}, 공격경로 : {2}")
    @MethodSource("provideForCalculateRouteToAttack")
    void calculateRouteToAttack(final Position source, final Position target, final List<Position> expectedRoute) {
        final List<Position> actualRoute = player.calculateRouteToAttack(source, target);
        assertThat(actualRoute).isEqualTo(expectedRoute);
    }

    private static Stream<Arguments> provideForCalculateRouteToAttack() {
        return Stream.of(
                Arguments.of(
                        Position.from("a2"),
                        Position.from("b3"),
                        List.of(Position.from("b3"))
                ),
                Arguments.of(
                        Position.from("d1"),
                        Position.from("d4"),
                        List.of(Position.from("d2"), Position.from("d3"), Position.from("d4"))
                )
        );
    }

    @DisplayName("목적지에 본인의 기물이 존재하면 목적지로 접근할 수 없어야 한다.")
    @Test
    void alreadyExistPieceAtTargetPositionException() {
        assertThatThrownBy(() -> player.move(Position.from("a1"), Position.from("a2")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("해당 위치에 이미 기물이 존재합니다.");
    }

    @DisplayName("출발지에 위치하는 본인의 기물을 목적지로 이동시킬 수 있어야 한다.")
    @Test
    void move() {
        player.move(Position.from("a1"), Position.from("b1"));
        final Map<Position, Piece> actual = player.getPieces();
        final Map<Position, Piece> expected = Map.of(
                Position.from("b1"), Rook.getInstance(),
                Position.from("a2"), Pawn.getWhitePawn(),
                Position.from("d1"), Queen.getInstance()
        );
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("본인의 기물이 해당 위치에 존재하는지 확인할 수 있어야 한다.")
    @ParameterizedTest
    @CsvSource(value = {"a3,false", "a2,true"})
    void contains(final String position, final boolean expected) {
        final boolean actual = player.contains(Position.from(position));
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("프로모션 가능한 폰이 존재하는지 확인할 수 있어야 한다.")
    @ParameterizedTest
    @CsvSource(value = {"a7,false", "a8,true"})
    void isPromotablePawnExist(final String position, final boolean expected) {
        final Player player = Player.of(Color.WHITE, Map.of(Position.from(position), Pawn.getWhitePawn()));
        final boolean actual = player.isPromotablePawnExist();
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("프로모션 가능한 폰이 존재하지 않으면 프로모션을 할 수 없어야 한다.")
    @Test
    void notExistPromotablePawnException() {
        assertThatThrownBy(() -> player.promotePawn("Queen"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("프로모션 가능한 폰이 존재하지 않습니다.");
    }

    @DisplayName("프로모션 가능한 폰이 존재하면 원하는 기물로 프로모션할 수 있어야 한다.")
    @Test
    void promotePawn() {
        final Position position = Position.from("a8");
        final Player player = Player.of(Color.WHITE, new HashMap<>(Map.of(position, Pawn.getWhitePawn())));
        player.promotePawn("Queen");

        final Map<Position, Piece> playerPieces = player.getPieces();
        assertThat(playerPieces.get(position)).isInstanceOf(Queen.class);
    }

    @DisplayName("킹이 살아있는지 확인할 수 있어야 한다.")
    @ParameterizedTest
    @MethodSource("provideForIsKingAlive")
    void isKingAlive(final Piece piece, final boolean expected) {
        final Player player = Player.of(Color.WHITE, new HashMap<>(Map.of(Position.from("a1"), piece)));
        final boolean actual = player.isKingAlive();
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideForIsKingAlive() {
        return Stream.of(
                Arguments.of(King.getInstance(), true),
                Arguments.of(Queen.getInstance(), false)
        );
    }

    @DisplayName("본인의 색상과 비교할 수 있어야 한다.")
    @ParameterizedTest
    @CsvSource(value = {"WHITE,true", "BLACK,false"})
    void isColorSame(final Color color, final boolean expected) {
        final boolean actual = player.isColorSame(color);
        assertThat(actual).isEqualTo(expected);
    }
}