package chess.domain.board;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @DisplayName("비숍은 북동쪽으로 직진할 수 있다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("bishopMoveNorthEastTestSet")
    void bishopMoveNorthEast(Position src, Position dest) {
        Piece piece = new Bishop(Color.BLACK);

        assertThat(piece.canMove(src, dest)).isTrue();
    }

    static Stream<Arguments> bishopMoveNorthEastTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("b2")),
                Arguments.of(Position.of("a1"), Position.of("c3")),
                Arguments.of(Position.of("a1"), Position.of("d4")),
                Arguments.of(Position.of("a1"), Position.of("e5")),
                Arguments.of(Position.of("a1"), Position.of("f6")),
                Arguments.of(Position.of("a1"), Position.of("g7")),
                Arguments.of(Position.of("a1"), Position.of("h8"))
        );
    }

    @DisplayName("비숍은 북서쪽으로 직진할 수 있다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("bishopMoveNorthWestTestSet")
    void bishopMoveNorthWest(Position src, Position dest) {
        Piece piece = new Bishop(Color.BLACK);

        assertThat(piece.canMove(src, dest)).isTrue();
    }

    static Stream<Arguments> bishopMoveNorthWestTestSet() {
        return Stream.of(
                Arguments.of(Position.of("h1"), Position.of("a8")),
                Arguments.of(Position.of("h1"), Position.of("b7")),
                Arguments.of(Position.of("h1"), Position.of("c6")),
                Arguments.of(Position.of("h1"), Position.of("d5")),
                Arguments.of(Position.of("h1"), Position.of("e4")),
                Arguments.of(Position.of("h1"), Position.of("f3")),
                Arguments.of(Position.of("h1"), Position.of("g2"))
        );
    }

    @DisplayName("비숍은 남동쪽으로 직진할 수 있다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("bishopMoveSouthEastTestSet")
    void bishopMoveSouthEast(Position src, Position dest) {
        Piece piece = new Bishop(Color.BLACK);

        assertThat(piece.canMove(src, dest)).isTrue();
    }

    static Stream<Arguments> bishopMoveSouthEastTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a8"), Position.of("b7")),
                Arguments.of(Position.of("a8"), Position.of("c6")),
                Arguments.of(Position.of("a8"), Position.of("d5")),
                Arguments.of(Position.of("a8"), Position.of("e4")),
                Arguments.of(Position.of("a8"), Position.of("f3")),
                Arguments.of(Position.of("a8"), Position.of("g2")),
                Arguments.of(Position.of("a8"), Position.of("h1"))
        );
    }

    @DisplayName("비숍은 남서쪽으로 직진할 수 있다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("bishopMoveSouthWestTestSet")
    void bishopMoveSouthWest(Position src, Position dest) {
        Piece piece = new Bishop(Color.BLACK);

        assertThat(piece.canMove(src, dest)).isTrue();
    }

    static Stream<Arguments> bishopMoveSouthWestTestSet() {
        return Stream.of(
                Arguments.of(Position.of("h8"), Position.of("a1")),
                Arguments.of(Position.of("h8"), Position.of("b2")),
                Arguments.of(Position.of("h8"), Position.of("c3")),
                Arguments.of(Position.of("h8"), Position.of("d4")),
                Arguments.of(Position.of("h8"), Position.of("e5")),
                Arguments.of(Position.of("h8"), Position.of("f6")),
                Arguments.of(Position.of("h8"), Position.of("g7"))
        );
    }

    @DisplayName("비숍은 북쪽으로 이동할 수 없다")
    @Test
    void bishopMoveNorth() {
        Piece piece = new Bishop(Color.BLACK);
        Position src = Position.of("d5");
        Position dest = Position.of("d6");

        assertThat(piece.canMove(src, dest)).isFalse();
    }
}
