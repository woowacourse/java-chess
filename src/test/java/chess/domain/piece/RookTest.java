package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
                Arguments.of(Position.valueOf("a3"), Position.valueOf("b3")),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("c3")),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("d3")),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("e3")),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("f3")),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("g3")),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("h3"))
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
                Arguments.of(Position.valueOf("h3"), Position.valueOf("a3")),
                Arguments.of(Position.valueOf("h3"), Position.valueOf("b3")),
                Arguments.of(Position.valueOf("h3"), Position.valueOf("c3")),
                Arguments.of(Position.valueOf("h3"), Position.valueOf("d3")),
                Arguments.of(Position.valueOf("h3"), Position.valueOf("e3")),
                Arguments.of(Position.valueOf("h3"), Position.valueOf("f3")),
                Arguments.of(Position.valueOf("h3"), Position.valueOf("g3"))
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
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a7")),
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a6")),
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a5")),
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a4")),
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a3")),
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a2")),
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a1"))
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
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a8")),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a7")),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a6")),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a5")),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a4")),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a3")),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a2"))
        );
    }

    @DisplayName("룩은 북서방향으로 한 칸 이동할 수 없다.")
    @Test
    void rookCannotMoveNorthEast() {
        Piece piece = new Rook(Color.BLACK);

        Position source = Position.valueOf("d3");
        Position destination = Position.valueOf("e4");

        assertThat(piece.canMove(source, destination)).isFalse();
    }
}
