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

class KingTest {

    static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of(Position.from("d6"), Position.from("d6")),
                Arguments.of(Position.from("e6"), Position.from("e6")),
                Arguments.of(Position.from("e5"), Position.from("e5")),
                Arguments.of(Position.from("e4"), Position.from("e4")),
                Arguments.of(Position.from("d4"), Position.from("d4")),
                Arguments.of(Position.from("c4"), Position.from("c4")),
                Arguments.of(Position.from("c5"), Position.from("c5")),
                Arguments.of(Position.from("c6"), Position.from("c6"))
        );
    }

    @DisplayName("킹이 이동한다.")
    @MethodSource
    @ParameterizedTest
    void move(Position target, Position expected) {
        Piece sut = new King(Color.WHITE, Position.from("d5"));
        Position actual = sut.move(ChessboardFactory.empty(), target).position();
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> moveException() {
        return Stream.of(
                Arguments.of(Position.from("b7")),
                Arguments.of(Position.from("d7")),
                Arguments.of(Position.from("f7")),
                Arguments.of(Position.from("f5")),
                Arguments.of(Position.from("f3")),
                Arguments.of(Position.from("d3")),
                Arguments.of(Position.from("b3")),
                Arguments.of(Position.from("b5"))
        );
    }

    @DisplayName("이동할 수 없는 위치를 입력하면 예외가 발생한다.")
    @MethodSource
    @ParameterizedTest
    void moveException(Position target) {
        Piece sut = new King(Color.WHITE, Position.from("d5"));
        assertThatThrownBy(() -> sut.move(ChessboardFactory.empty(), target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다: " + target);
    }

    static Stream<Arguments> movablePositions() {
        return Stream.of(
                Arguments.of(
                        Position.from("e6"),
                        Positions.of("d7", "e7", "f7", "f6", "f5", "e5", "d5", "d6")
                )
        );
    }

    @DisplayName("기물의 현재 위치에서 이동가능한 위치를 모두 찾는다.")
    @MethodSource
    @ParameterizedTest
    void movablePositions(Position source, Set<Position> expected) {
        Piece sut = new King(Color.BLACK, source);
        var actual = sut.movablePositions(ChessboardFactory.empty());
        assertThat(actual).containsAll(expected);
    }
}
