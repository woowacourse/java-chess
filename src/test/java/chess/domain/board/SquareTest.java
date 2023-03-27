package chess.domain.board;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.board.File.*;
import static chess.domain.board.Rank.*;
import static chess.domain.piece.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {
    static Stream<Arguments> distanceDummy() {
        return Stream.of(
                Arguments.of(Square.of(H, EIGHT), 5),
                Arguments.of(Square.of(C, EIGHT), 5),
                Arguments.of(Square.of(H, THREE), 5),
                Arguments.of(Square.of(D, FIVE), 1),
                Arguments.of(Square.of(A, ONE), 2)
        );
    }

    static Stream<Arguments> directionDummy() {
        return Stream.of(
                Arguments.of(NORTH, Square.of(C, FOUR)),
                Arguments.of(NORTH_EAST, Square.of(D, FOUR)),
                Arguments.of(EAST, Square.of(D, THREE)),
                Arguments.of(SOUTH_EAST, Square.of(D, TWO)),
                Arguments.of(SOUTH, Square.of(C, TWO)),
                Arguments.of(SOUTH_WEST, Square.of(B, TWO)),
                Arguments.of(WEST, Square.of(B, THREE)),
                Arguments.of(NORTH_WEST, Square.of(B, FOUR)),
                Arguments.of(NORTH_NORTH_EAST, Square.of(D, FIVE)),
                Arguments.of(NORTH_EAST_EAST, Square.of(E, FOUR)),
                Arguments.of(SOUTH_EAST_EAST, Square.of(E, TWO)),
                Arguments.of(SOUTH_SOUTH_EAST, Square.of(D, ONE)),
                Arguments.of(SOUTH_SOUTH_WEST, Square.of(B, ONE)),
                Arguments.of(SOUTH_WEST_WEST, Square.of(A, TWO)),
                Arguments.of(NORTH_WEST_WEST, Square.of(A, FOUR)),
                Arguments.of(NORTH_NORTH_WEST, Square.of(B, FIVE))
        );
    }

    @Test
    @DisplayName("Rank와 File을 받아서 생성한다.")
    void getAllSquares() {
        // expected
        List<Square> squares = Square.getAllSquares();

        assertThat(squares).hasSize(64);
    }

    @Test
    @DisplayName("Rank와 File이 동일하면 같은 Square를 반환한다.")
    void of() {
        // given
        Rank rank = Rank.ONE;
        File file = File.A;

        // when
        Square square = Square.of(file, rank);
        Square sameSquare = Square.of(file, rank);


        // expected
        assertThat(square).isSameAs(sameSquare);
    }

    @ParameterizedTest
    @MethodSource("distanceDummy")
    @DisplayName("다른 칸과의 거리를 계산한다.")
    void calculateDistance(final Square targetSquare, final int expectedDistance) {
        // given
        Square sourceSquare = Square.of(C, THREE);

        // expected
        assertThat(sourceSquare.calculateDistance(targetSquare)).isEqualTo(expectedDistance);
    }

    @ParameterizedTest
    @MethodSource("directionDummy")
    @DisplayName("방향에 따라 다음 칸을 반환한다.")
    void next(final Direction direction, final Square expectedSquare) {
        // given
        Square sourceSquare = Square.of(C, THREE);

        // expected
        assertThat(sourceSquare.next(direction)).isEqualTo(expectedSquare);

    }
}
