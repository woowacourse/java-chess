package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;
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
                Arguments.of(new WhitePawn('p', Team.WHITE, new Position(1, 2)), 'p'),
                Arguments.of(new BlackPawn('P', Team.BLACK, new Position(1, 7)), 'P'),
                Arguments.of(new Rook('r', Team.WHITE, new Position(1, 1)), 'r'),
                Arguments.of(new Rook('R', Team.BLACK, new Position(1, 8)), 'R'),
                Arguments.of(new Knight('n', Team.WHITE, new Position(2, 1)), 'n'),
                Arguments.of(new Knight('N', Team.BLACK, new Position(2, 8)), 'N'),
                Arguments.of(new Bishop('b', Team.WHITE, new Position(3, 1)), 'b'),
                Arguments.of(new Bishop('B', Team.BLACK, new Position(3, 8)), 'B'),
                Arguments.of(new Queen('q', Team.WHITE, new Position(4, 1)), 'q'),
                Arguments.of(new Queen('Q', Team.BLACK, new Position(4, 8)), 'Q'),
                Arguments.of(new King('k', Team.WHITE, new Position(5, 1)), 'k'),
                Arguments.of(new King('K', Team.BLACK, new Position(5, 8)), 'K')
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
                Arguments.of(new WhitePawn('p', Team.WHITE, new Position(1, 2)), new Position(1, 2)),
                Arguments.of(new BlackPawn('P', Team.BLACK, new Position(1, 7)), new Position(1, 7)),
                Arguments.of(new Rook('r', Team.WHITE, new Position(1, 1)), new Position(1, 1)),
                Arguments.of(new Rook('R', Team.BLACK, new Position(1, 8)), new Position(1, 8)),
                Arguments.of(new Knight('n', Team.WHITE, new Position(2, 1)), new Position(2, 1)),
                Arguments.of(new Knight('N', Team.BLACK, new Position(2, 8)), new Position(2, 8)),
                Arguments.of(new Bishop('b', Team.WHITE, new Position(3, 1)), new Position(3, 1)),
                Arguments.of(new Bishop('B', Team.BLACK, new Position(3, 8)), new Position(3, 8)),
                Arguments.of(new Queen('q', Team.WHITE, new Position(4, 1)), new Position(4, 1)),
                Arguments.of(new Queen('Q', Team.BLACK, new Position(4, 8)), new Position(4, 8)),
                Arguments.of(new King('k', Team.WHITE, new Position(5, 1)), new Position(5, 1)),
                Arguments.of(new King('K', Team.BLACK, new Position(5, 8)), new Position(5, 8))
        );
    }

    @DisplayName("초기화된 보드에서 말들의 이동 가능 여부 확인")
    @ParameterizedTest
    @MethodSource("getCasesForInitializedPieceMovable")
    void movable(Piece piece, Position to, boolean expectedMovable) {
        Board board = BoardFactory.createBoard();
        assertThat(piece.isMovable(board, to)).isEqualTo(expectedMovable);
    }

    private static Stream<Arguments> getCasesForInitializedPieceMovable() {
        return Stream.of(
                // Pawn
                Arguments.of(new WhitePawn('p', Team.WHITE, new Position(1, 2)), new Position(1, 3), true),
                Arguments.of(new WhitePawn('p', Team.WHITE, new Position(1, 2)), new Position(1, 4), true),
                Arguments.of(new BlackPawn('p', Team.WHITE, new Position(1, 2)), new Position(1, 5), false),
                Arguments.of(new BlackPawn('P', Team.BLACK, new Position(1, 7)), new Position(1, 6), true),
                Arguments.of(new BlackPawn('P', Team.BLACK, new Position(1, 7)), new Position(1, 5), true),
                Arguments.of(new BlackPawn('P', Team.BLACK, new Position(1, 7)), new Position(1, 4), false),

                //Rook
                Arguments.of(new Rook('r', Team.WHITE, new Position(1, 1)), new Position(1, 5), false),
                Arguments.of(new Rook('R', Team.BLACK, new Position(1, 8)), new Position(5, 8), false),

                //Knight
                Arguments.of(new Knight('n', Team.WHITE, new Position(2, 1)), new Position(1, 3), true),
                Arguments.of(new Knight('n', Team.WHITE, new Position(2, 1)), new Position(3, 3), true),
                Arguments.of(new Knight('n', Team.WHITE, new Position(2, 1)), new Position(4, 4), false),
                Arguments.of(new Knight('n', Team.WHITE, new Position(2, 1)), new Position(4, 2), false),
                Arguments.of(new Knight('N', Team.BLACK, new Position(2, 8)), new Position(1, 6), true),
                Arguments.of(new Knight('N', Team.BLACK, new Position(2, 8)), new Position(3, 6), true),
                Arguments.of(new Knight('N', Team.BLACK, new Position(2, 8)), new Position(4, 7), false),
                Arguments.of(new Knight('N', Team.BLACK, new Position(2, 8)), new Position(4, 4), false),

                //Bishop
                Arguments.of(new Bishop('b', Team.WHITE, new Position(3, 1)), new Position(4, 2), false),
                Arguments.of(new Bishop('B', Team.BLACK, new Position(3, 8)), new Position(4, 7), false),

                //Queen
                Arguments.of(new Queen('q', Team.WHITE, new Position(4, 1)), new Position(6, 3), false),
                Arguments.of(new Queen('Q', Team.BLACK, new Position(4, 8)), new Position(5, 7), false),

                //King
                Arguments.of(new King('k', Team.WHITE, new Position(5, 1)), new Position(5, 2), false),
                Arguments.of(new King('K', Team.BLACK, new Position(5, 8)), new Position(4, 8), false)
        );
    }
}
