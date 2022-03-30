package chess.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import chess.domain.piece.movable.Pawn;
import chess.domain.piece.movable.multiple.Queen;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(Color.WHITE, Map.of(
                Position.from("a2"), Pawn.getWhitePawn(),
                Position.from("d1"), Queen.getInstance()
        ));
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


}