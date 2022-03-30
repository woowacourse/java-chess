package chess.domain.piece;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @DisplayName("룩은 동쪽으로 직진할 수 있다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("rookMoveEastTestSet")
    void rookMoveEast(Position source, Position destination) {
        Piece piece = new Rook(Color.BLACK);

        assertThat(piece.canMove(source, destination)).isTrue();
    }

    static Stream<Arguments> rookMoveEastTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a3"), Position.of("b3")),
                Arguments.of(Position.of("a3"), Position.of("c3")),
                Arguments.of(Position.of("a3"), Position.of("d3")),
                Arguments.of(Position.of("a3"), Position.of("e3")),
                Arguments.of(Position.of("a3"), Position.of("f3")),
                Arguments.of(Position.of("a3"), Position.of("g3")),
                Arguments.of(Position.of("a3"), Position.of("h3"))
        );
    }

    @DisplayName("룩은 서쪽으로 직진할 수 있다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("rookMoveWestTestSet")
    void rookMoveWest(Position source, Position destination) {
        Piece piece = new Rook(Color.BLACK);

        assertThat(piece.canMove(source, destination)).isTrue();
    }

    static Stream<Arguments> rookMoveWestTestSet() {
        return Stream.of(
                Arguments.of(Position.of("h3"), Position.of("a3")),
                Arguments.of(Position.of("h3"), Position.of("b3")),
                Arguments.of(Position.of("h3"), Position.of("c3")),
                Arguments.of(Position.of("h3"), Position.of("d3")),
                Arguments.of(Position.of("h3"), Position.of("e3")),
                Arguments.of(Position.of("h3"), Position.of("f3")),
                Arguments.of(Position.of("h3"), Position.of("g3"))
        );
    }

    @DisplayName("룩은 남쪽으로 직진할 수 있다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("rookMoveSouthTestSet")
    void rookMoveSouth(Position source, Position destination) {
        Piece piece = new Rook(Color.BLACK);

        assertThat(piece.canMove(source, destination)).isTrue();
    }

    static Stream<Arguments> rookMoveSouthTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a8"), Position.of("a7")),
                Arguments.of(Position.of("a8"), Position.of("a6")),
                Arguments.of(Position.of("a8"), Position.of("a5")),
                Arguments.of(Position.of("a8"), Position.of("a4")),
                Arguments.of(Position.of("a8"), Position.of("a3")),
                Arguments.of(Position.of("a8"), Position.of("a2")),
                Arguments.of(Position.of("a8"), Position.of("a1"))
        );
    }

    @DisplayName("룩은 북쪽으로 직진할 수 있다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("rookMoveNorthTestSet")
    void rookMoveNorth(Position source, Position destination) {
        Piece piece = new Rook(Color.BLACK);

        assertThat(piece.canMove(source, destination)).isTrue();
    }

    static Stream<Arguments> rookMoveNorthTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("a8")),
                Arguments.of(Position.of("a1"), Position.of("a7")),
                Arguments.of(Position.of("a1"), Position.of("a6")),
                Arguments.of(Position.of("a1"), Position.of("a5")),
                Arguments.of(Position.of("a1"), Position.of("a4")),
                Arguments.of(Position.of("a1"), Position.of("a3")),
                Arguments.of(Position.of("a1"), Position.of("a2"))
        );
    }

    @DisplayName("룩은 북서방향으로 한 칸 이동할 수 없다.")
    @Test
    void rookCannotMoveNorthEast() {
        Piece piece = new Rook(Color.BLACK);

        Position source = Position.of("d3");
        Position destination = Position.of("e4");

        assertThat(piece.canMove(source, destination)).isFalse();
    }
}
