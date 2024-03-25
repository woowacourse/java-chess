package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.chessboard.Chessboard;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

class KnightTest {

    private static final String INITIAL_POSITION = "d5";

    static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of(Position.from("e7"), Position.from("e7")),
                Arguments.of(Position.from("f6"), Position.from("f6")),
                Arguments.of(Position.from("f4"), Position.from("f4")),
                Arguments.of(Position.from("e3"), Position.from("e3")),
                Arguments.of(Position.from("c3"), Position.from("c3")),
                Arguments.of(Position.from("b4"), Position.from("b4")),
                Arguments.of(Position.from("b6"), Position.from("b6")),
                Arguments.of(Position.from("c7"), Position.from("c7"))
        );
    }

    @DisplayName("나이트가 이동한다.")
    @MethodSource
    @ParameterizedTest
    void move(Position target, Position expected) {
        Piece sut = new Knight(Color.BLACK, Position.from(INITIAL_POSITION));
        Position actual = sut.move(Chessboard.empty(), target).position();
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> moveException() {
        return Stream.of(
                Arguments.of(Position.from("d6")),
                Arguments.of(Position.from("e6")),
                Arguments.of(Position.from("e5")),
                Arguments.of(Position.from("e4")),
                Arguments.of(Position.from("d4")),
                Arguments.of(Position.from("c4")),
                Arguments.of(Position.from("c5")),
                Arguments.of(Position.from("c6"))
        );
    }

    @DisplayName("이동할 수 없는 위치를 입력하면 예외가 발생한다.")
    @MethodSource
    @ParameterizedTest
    void moveException(Position target) {
        Piece sut = new Knight(Color.BLACK, Position.from(INITIAL_POSITION));
        assertThatThrownBy(() -> sut.move(Chessboard.empty(), target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다: " + target);
    }
}
