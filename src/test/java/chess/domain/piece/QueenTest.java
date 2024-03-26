package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.chessboard.ChessboardFactory;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;
import chess.domain.piece.attribute.Positions;

class QueenTest {

    private static final String INITIAL_POSITION = "d5";

    static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of(Position.from("a8"), Position.from("a8")),
                Arguments.of(Position.from("b7"), Position.from("b7")),
                Arguments.of(Position.from("c6"), Position.from("c6")),
                Arguments.of(Position.from("d8"), Position.from("d8")),
                Arguments.of(Position.from("d7"), Position.from("d7")),
                Arguments.of(Position.from("d6"), Position.from("d6")),
                Arguments.of(Position.from("e6"), Position.from("e6")),
                Arguments.of(Position.from("f7"), Position.from("f7")),
                Arguments.of(Position.from("g8"), Position.from("g8")),
                Arguments.of(Position.from("e5"), Position.from("e5")),
                Arguments.of(Position.from("f5"), Position.from("f5")),
                Arguments.of(Position.from("g5"), Position.from("g5")),
                Arguments.of(Position.from("h5"), Position.from("h5")),
                Arguments.of(Position.from("e4"), Position.from("e4")),
                Arguments.of(Position.from("f3"), Position.from("f3")),
                Arguments.of(Position.from("g2"), Position.from("g2")),
                Arguments.of(Position.from("h1"), Position.from("h1")),
                Arguments.of(Position.from("d4"), Position.from("d4")),
                Arguments.of(Position.from("d3"), Position.from("d3")),
                Arguments.of(Position.from("d2"), Position.from("d2")),
                Arguments.of(Position.from("d1"), Position.from("d1")),
                Arguments.of(Position.from("c4"), Position.from("c4")),
                Arguments.of(Position.from("b3"), Position.from("b3")),
                Arguments.of(Position.from("a2"), Position.from("a2")),
                Arguments.of(Position.from("a5"), Position.from("a5")),
                Arguments.of(Position.from("b5"), Position.from("b5")),
                Arguments.of(Position.from("c5"), Position.from("c5"))
        );
    }

    @DisplayName("퀸이 이동한다.")
    @MethodSource
    @ParameterizedTest
    void move(Position target, Position expected) {
        Piece sut = new Queen(Color.BLACK, Position.from(INITIAL_POSITION));
        Position actual = sut.move(ChessboardFactory.empty(), target).position();
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> moveException() {
        return Stream.of(
                Arguments.of(Position.from("e7")),
                Arguments.of(Position.from("f6")),
                Arguments.of(Position.from("f4")),
                Arguments.of(Position.from("e3")),
                Arguments.of(Position.from("c3")),
                Arguments.of(Position.from("b4")),
                Arguments.of(Position.from("b6")),
                Arguments.of(Position.from("c7"))
        );
    }

    @DisplayName("이동할 수 없는 위치를 입력하면 예외가 발생한다.")
    @MethodSource
    @ParameterizedTest
    void moveException(Position target) {
        Piece sut = new Queen(Color.BLACK, Position.from(INITIAL_POSITION));
        assertThatThrownBy(() -> sut.move(ChessboardFactory.empty(), target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다: " + target);
    }

    static Stream<Arguments> movablePositions() {
        return Stream.of(
                Arguments.of(
                        Position.from("d1"),
                        Positions.of(
                                "a1", "b1", "c1",
                                "a4", "b3", "c2",
                                "d8", "d7", "d6", "d5", "d4", "d3", "d2",
                                "e2", "f3", "g4", "h5",
                                "e1", "f1", "g1", "h1"
                        )
                )
        );
    }

    @DisplayName("기물의 현재 위치에서 이동가능한 위치를 모두 찾는다.")
    @MethodSource
    @ParameterizedTest
    void movablePositions(Position source, Set<Position> expected) {
        Piece sut = new Queen(Color.BLACK, source);
        var actual = sut.movablePositions(ChessboardFactory.empty());
        assertThat(actual).containsAll(expected);
    }
}
