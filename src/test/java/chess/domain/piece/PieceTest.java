package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import static chess.domain.attribute.Color.WHITE;

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
                movableSquaresOfKnight(),
                movableSquaresOfStartingPawn(),
                movableSquaresOfPawn()
        ).flatMap(stream -> stream);
    }

    @DisplayName("기물이 이동할 수 있는 칸을 찾는다.")
    @MethodSource
    @ParameterizedTest
    void movableSquares(Piece piece, Square currentSquare, Set<Square> expected) {
        assertThat(piece.movableSquares(currentSquare)).isEqualTo(expected);
    }
}
