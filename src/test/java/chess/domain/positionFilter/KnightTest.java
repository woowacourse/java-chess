package chess.domain.positionFilter;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Row;
import chess.domain.game.PositionsFilter;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    /**
     * ........  8 (rank 8)
     * ........  7
     * ..r.P...  6
     * ..P.P...  5
     * ...np...  4
     * .B.ppq..  3
     * ........  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("실제로 움직일 수 있는 위치를 모두 가져온다.")
    void generateMovablePositions() {
        Position position = new Position(Row.FOUR, Column.D);
        Piece piece = new Piece(PieceType.KNIGHT, Color.WHITE);
        Board board = new Board(
                Map.of(
                        position, piece,
                        new Position(Row.THREE, Column.F), new Piece(PieceType.QUEEN, Color.WHITE),
                        new Position(Row.SIX, Column.C), new Piece(PieceType.ROOK, Color.WHITE),
                        new Position(Row.FOUR, Column.E), new Piece(PieceType.WHITE_PAWN, Color.WHITE),
                        new Position(Row.THREE, Column.D), new Piece(PieceType.WHITE_PAWN, Color.WHITE),
                        new Position(Row.THREE, Column.E), new Piece(PieceType.WHITE_PAWN, Color.WHITE),
                        new Position(Row.SIX, Column.E), new Piece(PieceType.BLACK_PAWN, Color.BLACK),
                        new Position(Row.FIVE, Column.C), new Piece(PieceType.BLACK_PAWN, Color.BLACK),
                        new Position(Row.FIVE, Column.E), new Piece(PieceType.BLACK_PAWN, Color.BLACK),
                        new Position(Row.THREE, Column.B), new Piece(PieceType.BISHOP, Color.BLACK)
                )
        );

        List<Position> movablePositions = new PositionsFilter().generateValidPositions(
                piece.generateAllDirectionPositions(position), piece, board);

        assertThat(movablePositions).containsExactlyInAnyOrder(
                new Position(Row.SIX, Column.E),
                new Position(Row.FIVE, Column.F),
                new Position(Row.TWO, Column.E),
                new Position(Row.TWO, Column.C),
                new Position(Row.THREE, Column.B),
                new Position(Row.FIVE, Column.B)
        );
    }
}
