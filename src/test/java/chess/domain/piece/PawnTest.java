package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.ChessboardFactory;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;
import chess.domain.piece.attribute.Positions;

class PawnTest {

    @DisplayName("폰이 이동한다.")
    @Test
    void move() {
        Piece sut = new Pawn(Color.WHITE, Position.from("d3"));
        Position actual = sut.move(ChessboardFactory.empty(), Position.from("d4")).position();
        assertThat(actual).isEqualTo(Position.from("d4"));
    }

    @DisplayName("이동할 수 없는 위치를 입력받으면 예외를 발생한다.")
    @Test
    void moveException() {
        Piece sut = new Pawn(Color.WHITE, Position.from("d5"));
        assertThatCode(() -> sut.move(ChessboardFactory.empty(), Position.from("e3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다: E3");
    }

    static Stream<Arguments> movablePositions() {
        return Stream.of(
                Arguments.of(
                        Color.WHITE,
                        ChessboardFactory.empty(),
                        Position.from("e6"),
                        Positions.of("e7")
                ),
                Arguments.of(
                        Color.WHITE,
                        ChessboardFactory.create(),
                        Position.from("e6"),
                        Positions.of("d7", "f7")
                ),
                Arguments.of(
                        Color.BLACK,
                        ChessboardFactory.empty(),
                        Position.from("d3"),
                        Positions.of("d2")
                ),
                Arguments.of(
                        Color.BLACK,
                        ChessboardFactory.create(),
                        Position.from("d3"),
                        Positions.of("c2", "e2")
                )
        );
    }

    @DisplayName("기물의 현재 위치에서 이동가능한 위치를 모두 찾는다.")
    @MethodSource
    @ParameterizedTest
    void movablePositions(Color color, Chessboard chessboard, Position source, Set<Position> expected) {
        Piece sut = new Pawn(color, source);
        var actual = sut.movablePositions(chessboard);
        System.out.println(actual);
        assertThat(actual).containsAll(expected);
    }
}
