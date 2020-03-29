package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;
import chess.domain.result.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
                Arguments.of(new BlackPawn('P', Team.BLACK, new Position(1, 7)), new Position(1, 6), true),
                Arguments.of(new BlackPawn('P', Team.BLACK, new Position(1, 7)), new Position(1, 5), true),

                //Knight
                Arguments.of(new Knight('n', Team.WHITE, new Position(2, 1)), new Position(1, 3), true),
                Arguments.of(new Knight('n', Team.WHITE, new Position(2, 1)), new Position(3, 3), true),
                Arguments.of(new Knight('N', Team.BLACK, new Position(2, 8)), new Position(1, 6), true),
                Arguments.of(new Knight('N', Team.BLACK, new Position(2, 8)), new Position(3, 6), true)
        );
    }

    @DisplayName("초기화된 보드에서 말들의 이동 불가능 여부 확인")
    @ParameterizedTest
    @MethodSource("getCasesForInitializedPieceUnMovable")
    void movable(Piece piece, Position to) {
        Board board = BoardFactory.createBoard();
        assertThatThrownBy(() -> {
            piece.isMovable(board, to);
        }).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("해당 포지션으로 이동할 수 없습니다.");
    }

    private static Stream<Arguments> getCasesForInitializedPieceUnMovable() {
        return Stream.of(
                //Pawn
                Arguments.of(new BlackPawn('p', Team.WHITE, new Position(1, 2)), new Position(1, 5)),
                Arguments.of(new BlackPawn('P', Team.BLACK, new Position(1, 7)), new Position(1, 4)),

                //Rook
                Arguments.of(new Rook('r', Team.WHITE, new Position(1, 1)), new Position(1, 5)),
                Arguments.of(new Rook('R', Team.BLACK, new Position(1, 8)), new Position(5, 8)),

                //Knight
                Arguments.of(new Knight('n', Team.WHITE, new Position(2, 1)), new Position(4, 4)),
                Arguments.of(new Knight('n', Team.WHITE, new Position(2, 1)), new Position(4, 2)),
                Arguments.of(new Knight('N', Team.BLACK, new Position(2, 8)), new Position(4, 7)),
                Arguments.of(new Knight('N', Team.BLACK, new Position(2, 8)), new Position(4, 4)),

                //Bishop
                Arguments.of(new Bishop('b', Team.WHITE, new Position(3, 1)), new Position(4, 2)),
                Arguments.of(new Bishop('B', Team.BLACK, new Position(3, 8)), new Position(4, 7)),

                //Queen
                Arguments.of(new Queen('q', Team.WHITE, new Position(4, 1)), new Position(6, 3)),
                Arguments.of(new Queen('Q', Team.BLACK, new Position(4, 8)), new Position(5, 7)),

                //King
                Arguments.of(new King('k', Team.WHITE, new Position(5, 1)), new Position(5, 2)),
                Arguments.of(new King('K', Team.BLACK, new Position(5, 8)), new Position(4, 8))
        );
    }

    @DisplayName("체스 말 각각의 점수 확인")
    @ParameterizedTest
    @MethodSource("getCasesForPieceScore")
    void pieceScore(Piece piece, double expectedScore) {
        assertThat(PieceType.getScoreOf(piece)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getCasesForPieceScore() {
        return Stream.of(
                Arguments.of(new WhitePawn('p', Team.WHITE, new Position(1, 2)), 1),
                Arguments.of(new BlackPawn('P', Team.BLACK, new Position(1, 7)), 1),
                Arguments.of(new Rook('r', Team.WHITE, new Position(1, 1)), 5),
                Arguments.of(new Rook('R', Team.BLACK, new Position(1, 8)), 5),
                Arguments.of(new Knight('n', Team.WHITE, new Position(2, 1)), 2.5),
                Arguments.of(new Knight('N', Team.BLACK, new Position(2, 8)), 2.5),
                Arguments.of(new Bishop('b', Team.WHITE, new Position(3, 1)), 3),
                Arguments.of(new Bishop('B', Team.BLACK, new Position(3, 8)), 3),
                Arguments.of(new Queen('q', Team.WHITE, new Position(4, 1)), 9),
                Arguments.of(new Queen('Q', Team.BLACK, new Position(4, 8)), 9),
                Arguments.of(new King('k', Team.WHITE, new Position(5, 1)), 0),
                Arguments.of(new King('K', Team.BLACK, new Position(5, 8)), 0)
        );
    }
}
