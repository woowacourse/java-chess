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
import chess.domain.attribute.Position;
import chess.domain.attribute.Rank;

class PieceTest {

    private static Stream<Arguments> movablePositionsOfKing() {
        return Stream.of(
                Arguments.of(
                        new King(WHITE),
                        Position.of(File.startKingFile(), Rank.startRankOf(WHITE)),
                        Set.of(
                                Position.of(File.D, Rank.ONE),
                                Position.of(File.F, Rank.ONE),
                                Position.of(File.D, Rank.TWO),
                                Position.of(File.E, Rank.TWO),
                                Position.of(File.F, Rank.TWO)
                        )
                )
        );
    }

    private static Stream<Arguments> movablePositionsOfQueen() {
        return Stream.of(
                Arguments.of(
                        new Queen(WHITE),
                        Position.of(File.D, Rank.FIVE),
                        Set.of(
                                Position.of(File.D, EIGHT),
                                Position.of(File.D, SEVEN),
                                Position.of(File.D, SIX),
                                Position.of(File.D, FOUR),
                                Position.of(File.D, THREE),
                                Position.of(File.D, TWO),
                                Position.of(File.D, ONE),
                                Position.of(File.A, FIVE),
                                Position.of(File.B, FIVE),
                                Position.of(File.C, FIVE),
                                Position.of(File.E, FIVE),
                                Position.of(File.F, FIVE),
                                Position.of(File.G, FIVE),
                                Position.of(File.H, FIVE),
                                Position.of(File.A, EIGHT),
                                Position.of(File.A, TWO),
                                Position.of(File.B, SEVEN),
                                Position.of(File.B, THREE),
                                Position.of(File.C, SIX),
                                Position.of(File.C, FOUR),
                                Position.of(File.E, SIX),
                                Position.of(File.E, FOUR),
                                Position.of(File.F, SEVEN),
                                Position.of(File.F, THREE),
                                Position.of(File.G, EIGHT),
                                Position.of(File.G, TWO),
                                Position.of(File.H, ONE)
                        )
                )
        );
    }

    private static Stream<Arguments> movablePositionsOfBishop() {
        return Stream.of(
                Arguments.of(
                        new Bishop(WHITE),
                        Position.of(File.D, FIVE),
                        Set.of(
                                Position.of(File.A, EIGHT),
                                Position.of(File.A, TWO),
                                Position.of(File.B, SEVEN),
                                Position.of(File.B, THREE),
                                Position.of(File.C, SIX),
                                Position.of(File.C, FOUR),
                                Position.of(File.E, SIX),
                                Position.of(File.E, FOUR),
                                Position.of(File.F, SEVEN),
                                Position.of(File.F, THREE),
                                Position.of(File.G, EIGHT),
                                Position.of(File.G, TWO),
                                Position.of(File.H, ONE)
                        )
                )
        );
    }

    private static Stream<Arguments> movablePositionsOfKnight() {
        return Stream.of(
                Arguments.of(
                        new Knight(WHITE),
                        Position.of(File.startKnightFileOf(0), Rank.startRankOf(WHITE)),
                        Set.of(
                                Position.of(File.A, Rank.THREE),
                                Position.of(File.C, Rank.THREE),
                                Position.of(File.D, Rank.TWO)
                        )
                )
        );
    }

    private static Stream<Arguments> movablePositionsOfRook() {
        return Stream.of(
                Arguments.of(
                        new Rook(WHITE),
                        Position.of(File.D, FIVE),
                        Set.of(
                                Position.of(File.D, EIGHT),
                                Position.of(File.D, SEVEN),
                                Position.of(File.D, SIX),
                                Position.of(File.D, FOUR),
                                Position.of(File.D, THREE),
                                Position.of(File.D, TWO),
                                Position.of(File.D, ONE),
                                Position.of(File.A, FIVE),
                                Position.of(File.B, FIVE),
                                Position.of(File.C, FIVE),
                                Position.of(File.E, FIVE),
                                Position.of(File.F, FIVE),
                                Position.of(File.G, FIVE),
                                Position.of(File.H, FIVE)
                        )
                )
        );
    }

    private static Stream<Arguments> movablePositionsOfStartingPawn() {
        return Stream.of(
                Arguments.of(
                        new StartingPawn(WHITE),
                        Position.of(File.startPawnFileOf(0), Rank.startPawnRankOf(WHITE)),
                        Set.of(
                                Position.of(File.A, Rank.THREE),
                                Position.of(File.A, Rank.FOUR),
                                Position.of(File.B, Rank.THREE)
                        )
                ),
                Arguments.of(
                        new StartingPawn(WHITE),
                        Position.of(File.startPawnFileOf(1), Rank.startPawnRankOf(WHITE)),
                        Set.of(
                                Position.of(File.A, Rank.THREE),
                                Position.of(File.B, Rank.THREE),
                                Position.of(File.C, Rank.THREE),
                                Position.of(File.B, Rank.FOUR)
                        )
                )
        );
    }

    private static Stream<Arguments> movablePositionsOfPawn() {
        return Stream.of(
                Arguments.of(
                        new Pawn(WHITE),
                        Position.of(File.B, Rank.THREE),
                        Set.of(
                                Position.of(File.A, Rank.FOUR),
                                Position.of(File.B, Rank.FOUR),
                                Position.of(File.C, Rank.FOUR)
                        )
                )
        );
    }

    private static Stream<Arguments> movablePositions() {
        return Stream.of(
                movablePositionsOfKing(),
                movablePositionsOfQueen(),
                movablePositionsOfBishop(),
                movablePositionsOfRook(),
                movablePositionsOfKnight(),
                movablePositionsOfStartingPawn(),
                movablePositionsOfPawn()
        ).flatMap(stream -> stream);
    }

    @DisplayName("기물이 이동할 수 있는 칸을 찾는다.")
    @MethodSource
    @ParameterizedTest
    void movablePositions(Piece piece, Position currentPosition, Set<Position> expected) {
        Set<Position> actual = piece.move(currentPosition);
        assertThat(actual).isEqualTo(expected);
    }
}
