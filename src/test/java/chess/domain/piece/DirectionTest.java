package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.piece.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {
    static Stream<Arguments> directionDummy() {
        return Stream.of(
                //동서남북
                Arguments.of(Square.of(File.A, Rank.ONE), Square.of(File.A, Rank.TWO), NORTH),
                Arguments.of(Square.of(File.A, Rank.ONE), Square.of(File.B, Rank.TWO), NORTH_EAST),
                Arguments.of(Square.of(File.A, Rank.ONE), Square.of(File.B, Rank.ONE), EAST),
                Arguments.of(Square.of(File.B, Rank.TWO), Square.of(File.C, Rank.ONE), SOUTH_EAST),
                Arguments.of(Square.of(File.B, Rank.TWO), Square.of(File.B, Rank.ONE), SOUTH),
                Arguments.of(Square.of(File.B, Rank.TWO), Square.of(File.A, Rank.ONE), SOUTH_WEST),
                Arguments.of(Square.of(File.B, Rank.TWO), Square.of(File.A, Rank.TWO), WEST),
                Arguments.of(Square.of(File.B, Rank.TWO), Square.of(File.A, Rank.THREE), NORTH_WEST),
                //나이트
                Arguments.of(Square.of(File.C, Rank.THREE), Square.of(File.D, Rank.FIVE), NORTH_NORTH_EAST),
                Arguments.of(Square.of(File.C, Rank.THREE), Square.of(File.E, Rank.FOUR), NORTH_EAST_EAST),
                Arguments.of(Square.of(File.C, Rank.THREE), Square.of(File.E, Rank.TWO), SOUTH_EAST_EAST),
                Arguments.of(Square.of(File.C, Rank.THREE), Square.of(File.D, Rank.ONE), SOUTH_SOUTH_EAST),
                Arguments.of(Square.of(File.C, Rank.THREE), Square.of(File.B, Rank.ONE), SOUTH_SOUTH_WEST),
                Arguments.of(Square.of(File.C, Rank.THREE), Square.of(File.A, Rank.TWO), SOUTH_WEST_WEST),
                Arguments.of(Square.of(File.C, Rank.THREE), Square.of(File.A, Rank.FOUR), NORTH_WEST_WEST),
                Arguments.of(Square.of(File.C, Rank.THREE), Square.of(File.B, Rank.FIVE), NORTH_NORTH_WEST)
        );
    }

    @ParameterizedTest
    @MethodSource("directionDummy")
    @DisplayName("두 Square에 대한 방향을 구한다.")
    void of(Square sourceSquare, Square targetSquare, Direction expectedDirection) {
        // expected
        assertThat(Direction.of(sourceSquare, targetSquare)).isEqualTo(expectedDirection);
    }

}
