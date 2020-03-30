package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PieceTest {
    @DisplayName("체스 말 각각의 표식")
    @ParameterizedTest
    @MethodSource("getCasesForPieceRepresentation")
    void representationTest(Piece piece, char expectedRepresentation) {
        assertThat(piece.getRepresentation()).isEqualTo(expectedRepresentation);
    }

    private static Stream<Arguments> getCasesForPieceRepresentation() {
        return Stream.of(
                Arguments.of(new WhitePawn(new WhitePawnMoveStrategy(), 'p', Team.WHITE, new Position(1, 2)), 'p'),
                Arguments.of(new BlackPawn(new BlackPawnMoveStrategy(), 'P', Team.BLACK, new Position(1, 7)), 'P'),
                Arguments.of(new Rook(new RookMoveStrategy(), 'r', Team.WHITE, new Position(1, 1)), 'r'),
                Arguments.of(new Rook(new RookMoveStrategy(), 'R', Team.BLACK, new Position(1, 8)), 'R'),
                Arguments.of(new Knight(new KnightMoveStrategy(), 'n', Team.WHITE, new Position(2, 1)), 'n'),
                Arguments.of(new Knight(new KnightMoveStrategy(), 'N', Team.BLACK, new Position(2, 8)), 'N'),
                Arguments.of(new Bishop(new BishopMoveStrategy(), 'b', Team.WHITE, new Position(3, 1)), 'b'),
                Arguments.of(new Bishop(new BishopMoveStrategy(), 'B', Team.BLACK, new Position(3, 8)), 'B'),
                Arguments.of(new Queen(new QueenMoveStrategy(), 'q', Team.WHITE, new Position(4, 1)), 'q'),
                Arguments.of(new Queen(new QueenMoveStrategy(), 'Q', Team.BLACK, new Position(4, 8)), 'Q'),
                Arguments.of(new King(new KingMoveStrategy(), 'k', Team.WHITE, new Position(5, 1)), 'k'),
                Arguments.of(new King(new KingMoveStrategy(), 'K', Team.BLACK, new Position(5, 8)), 'K')
        );
    }

    @DisplayName("각 말들의 처음 포지션 초기화")
    @ParameterizedTest
    @MethodSource("getCasesForPieceInitialPosition")
    void initializePiecePosition(Piece piece, Position expectedPosition) {
        assertThat(piece.getPosition()).isEqualTo(expectedPosition);
    }

    private static Stream<Arguments> getCasesForPieceInitialPosition() {
        return Stream.of(
                Arguments.of(new WhitePawn(new WhitePawnMoveStrategy(), 'p', Team.WHITE, new Position(1, 2)), new Position(1, 2)),
                Arguments.of(new BlackPawn(new BlackPawnMoveStrategy(), 'P', Team.BLACK, new Position(1, 7)), new Position(1, 7)),
                Arguments.of(new Rook(new RookMoveStrategy(), 'r', Team.WHITE, new Position(1, 1)), new Position(1, 1)),
                Arguments.of(new Rook(new RookMoveStrategy(), 'R', Team.BLACK, new Position(1, 8)), new Position(1, 8)),
                Arguments.of(new Knight(new KnightMoveStrategy(), 'n', Team.WHITE, new Position(2, 1)), new Position(2, 1)),
                Arguments.of(new Knight(new KnightMoveStrategy(), 'N', Team.BLACK, new Position(2, 8)), new Position(2, 8)),
                Arguments.of(new Bishop(new BishopMoveStrategy(), 'b', Team.WHITE, new Position(3, 1)), new Position(3, 1)),
                Arguments.of(new Bishop(new BishopMoveStrategy(), 'B', Team.BLACK, new Position(3, 8)), new Position(3, 8)),
                Arguments.of(new Queen(new QueenMoveStrategy(), 'q', Team.WHITE, new Position(4, 1)), new Position(4, 1)),
                Arguments.of(new Queen(new QueenMoveStrategy(), 'Q', Team.BLACK, new Position(4, 8)), new Position(4, 8)),
                Arguments.of(new King(new KingMoveStrategy(), 'k', Team.WHITE, new Position(5, 1)), new Position(5, 1)),
                Arguments.of(new King(new KingMoveStrategy(), 'K', Team.BLACK, new Position(5, 8)), new Position(5, 8))
        );
    }

    @DisplayName("체스 말 각각의 점수 확인")
    @ParameterizedTest
    @MethodSource("getCasesForPieceScore")
    void pieceScore(Piece piece, double expectedScore) {
        assertThat(piece.score()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getCasesForPieceScore() {
        return Stream.of(
                Arguments.of(new WhitePawn(new WhitePawnMoveStrategy(), 'p', Team.WHITE, new Position(1, 2)), 1),
                Arguments.of(new BlackPawn(new BlackPawnMoveStrategy(), 'P', Team.BLACK, new Position(1, 7)), 1),
                Arguments.of(new Rook(new RookMoveStrategy(), 'r', Team.WHITE, new Position(1, 1)), 5),
                Arguments.of(new Rook(new RookMoveStrategy(), 'R', Team.BLACK, new Position(1, 8)), 5),
                Arguments.of(new Knight(new KnightMoveStrategy(), 'n', Team.WHITE, new Position(2, 1)), 2.5),
                Arguments.of(new Knight(new KnightMoveStrategy(), 'N', Team.BLACK, new Position(2, 8)), 2.5),
                Arguments.of(new Bishop(new BishopMoveStrategy(), 'b', Team.WHITE, new Position(3, 1)), 3),
                Arguments.of(new Bishop(new BishopMoveStrategy(), 'B', Team.BLACK, new Position(3, 8)), 3),
                Arguments.of(new Queen(new QueenMoveStrategy(), 'q', Team.WHITE, new Position(4, 1)), 9),
                Arguments.of(new Queen(new QueenMoveStrategy(), 'Q', Team.BLACK, new Position(4, 8)), 9),
                Arguments.of(new King(new KingMoveStrategy(), 'k', Team.WHITE, new Position(5, 1)), 0),
                Arguments.of(new King(new KingMoveStrategy(), 'K', Team.BLACK, new Position(5, 8)), 0)
        );
    }
}
