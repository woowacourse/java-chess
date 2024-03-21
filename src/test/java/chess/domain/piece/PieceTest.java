package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import static chess.domain.attribute.Color.WHITE;
import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.FIVE;
import static chess.domain.attribute.Rank.FOUR;
import static chess.domain.attribute.Rank.ONE;
import static chess.domain.attribute.Rank.SEVEN;
import static chess.domain.attribute.Rank.SIX;
import static chess.domain.attribute.Rank.THREE;
import static chess.domain.attribute.Rank.TWO;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.attribute.File;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Square;

class PieceTest {

    private static Stream<Arguments> movableSquaresOfKing() {
        return Stream.of(
                Arguments.of(
                        new King(WHITE),
                        Square.of(File.startKingFile(), Rank.startRankOf(WHITE)),
                        Set.of(
                                Square.of(File.D, Rank.ONE),
                                Square.of(File.F, Rank.ONE),
                                Square.of(File.D, Rank.TWO),
                                Square.of(File.E, Rank.TWO),
                                Square.of(File.F, Rank.TWO)
                        )
                )
        );
    }

    private static Stream<Arguments> movableSquaresOfQueen() {
        return Stream.of(
                Arguments.of(
                        new Queen(WHITE),
                        Square.of(File.D, Rank.FIVE),
                        Set.of(
                                Square.of(File.D, EIGHT),
                                Square.of(File.D, SEVEN),
                                Square.of(File.D, SIX),
                                Square.of(File.D, FOUR),
                                Square.of(File.D, THREE),
                                Square.of(File.D, TWO),
                                Square.of(File.D, ONE),
                                Square.of(File.A, FIVE),
                                Square.of(File.B, FIVE),
                                Square.of(File.C, FIVE),
                                Square.of(File.E, FIVE),
                                Square.of(File.F, FIVE),
                                Square.of(File.G, FIVE),
                                Square.of(File.H, FIVE),
                                Square.of(File.A, EIGHT),
                                Square.of(File.A, TWO),
                                Square.of(File.B, SEVEN),
                                Square.of(File.B, THREE),
                                Square.of(File.C, SIX),
                                Square.of(File.C, FOUR),
                                Square.of(File.E, SIX),
                                Square.of(File.E, FOUR),
                                Square.of(File.F, SEVEN),
                                Square.of(File.F, THREE),
                                Square.of(File.G, EIGHT),
                                Square.of(File.G, TWO),
                                Square.of(File.H, ONE)
                        )
                )
        );
    }

    private static Stream<Arguments> movableSquaresOfBishop() {
        return Stream.of(
                Arguments.of(
                        new Bishop(WHITE),
                        Square.of(File.D, FIVE),
                        Set.of(
                                Square.of(File.A, EIGHT),
                                Square.of(File.A, TWO),
                                Square.of(File.B, SEVEN),
                                Square.of(File.B, THREE),
                                Square.of(File.C, SIX),
                                Square.of(File.C, FOUR),
                                Square.of(File.E, SIX),
                                Square.of(File.E, FOUR),
                                Square.of(File.F, SEVEN),
                                Square.of(File.F, THREE),
                                Square.of(File.G, EIGHT),
                                Square.of(File.G, TWO),
                                Square.of(File.H, ONE)
                        )
                )
        );
    }

    private static Stream<Arguments> movableSquaresOfKnight() {
        return Stream.of(
                Arguments.of(
                        new Knight(WHITE),
                        Square.of(File.startKnightFileOf(0), Rank.startRankOf(WHITE)),
                        Set.of(
                                Square.of(File.A, Rank.THREE),
                                Square.of(File.C, Rank.THREE),
                                Square.of(File.D, Rank.TWO)
                        )
                )
        );
    }

    private static Stream<Arguments> movableSquaresOfRook() {
        return Stream.of(
                Arguments.of(
                        new Rook(WHITE),
                        Square.of(File.D, FIVE),
                        Set.of(
                                Square.of(File.D, EIGHT),
                                Square.of(File.D, SEVEN),
                                Square.of(File.D, SIX),
                                Square.of(File.D, FOUR),
                                Square.of(File.D, THREE),
                                Square.of(File.D, TWO),
                                Square.of(File.D, ONE),
                                Square.of(File.A, FIVE),
                                Square.of(File.B, FIVE),
                                Square.of(File.C, FIVE),
                                Square.of(File.E, FIVE),
                                Square.of(File.F, FIVE),
                                Square.of(File.G, FIVE),
                                Square.of(File.H, FIVE)
                        )
                )
        );
    }

    private static Stream<Arguments> movableSquaresOfStartingPawn() {
        return Stream.of(
                Arguments.of(
                        new StartingPawn(WHITE),
                        Square.of(File.startPawnFileOf(0), Rank.startPawnRankOf(WHITE)),
                        Set.of(
                                Square.of(File.A, Rank.THREE),
                                Square.of(File.A, Rank.FOUR),
                                Square.of(File.B, Rank.THREE)
                        )
                ),
                Arguments.of(
                        new StartingPawn(WHITE),
                        Square.of(File.startPawnFileOf(1), Rank.startPawnRankOf(WHITE)),
                        Set.of(
                                Square.of(File.A, Rank.THREE),
                                Square.of(File.B, Rank.THREE),
                                Square.of(File.C, Rank.THREE),
                                Square.of(File.B, Rank.FOUR)
                        )
                )
        );
    }

    private static Stream<Arguments> movableSquaresOfPawn() {
        return Stream.of(
                Arguments.of(
                        new Pawn(WHITE),
                        Square.of(File.B, Rank.THREE),
                        Set.of(
                                Square.of(File.A, Rank.FOUR),
                                Square.of(File.B, Rank.FOUR),
                                Square.of(File.C, Rank.FOUR)
                        )
                )
        );
    }

    private static Stream<Arguments> movableSquares() {
        return Stream.of(
                movableSquaresOfKing(),
                movableSquaresOfQueen(),
                movableSquaresOfBishop(),
                movableSquaresOfRook(),
                movableSquaresOfKnight(),
                movableSquaresOfStartingPawn(),
                movableSquaresOfPawn()
        ).flatMap(stream -> stream);
    }

    @DisplayName("기물이 이동할 수 있는 칸을 찾는다.")
    @MethodSource
    @ParameterizedTest
    void movableSquares(Piece piece, Square currentSquare, Set<Square> expected) {
        Set<Square> actual = piece.movableSquaresFrom(currentSquare);
        assertThat(actual).isEqualTo(expected);
    }
}
