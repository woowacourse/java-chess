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

class KingTest {
    /**
     * ........  8 (rank 8)
     * ...P....  7
     * ........  6
     * ........  5
     * .....P..  4
     * ..p.....  3
     * ..Pkp...  2
     * ..NbR...  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("실제로 움직일 수 있는 위치를 모두 가져온다.")
    void generateMovablePositions() {
        Position position = new Position(Row.TWO, Column.D);
        Piece piece = new Piece(PieceType.KING, Color.WHITE);
        Board board = new Board(
                Map.of(
                        position, piece,
                        new Position(Row.TWO, Column.E), new Piece(PieceType.WHITE_PAWN, Color.WHITE),
                        new Position(Row.ONE, Column.D), new Piece(PieceType.BISHOP, Color.WHITE),
                        new Position(Row.THREE, Column.C), new Piece(PieceType.BLACK_PAWN, Color.WHITE),
                        new Position(Row.SEVEN, Column.D), new Piece(PieceType.BLACK_PAWN, Color.BLACK),
                        new Position(Row.FOUR, Column.F), new Piece(PieceType.BLACK_PAWN, Color.BLACK),
                        new Position(Row.ONE, Column.E), new Piece(PieceType.ROOK, Color.BLACK),
                        new Position(Row.ONE, Column.C), new Piece(PieceType.KNIGHT, Color.BLACK),
                        new Position(Row.TWO, Column.C), new Piece(PieceType.BLACK_PAWN, Color.BLACK)

                )
        );

        List<Position> movablePositions = new PositionsFilter().generateValidPositions(
                piece.generateAllDirectionPositions(position), piece, board);

        assertThat(movablePositions).containsExactlyInAnyOrder(
                new Position(Row.THREE, Column.D),
                new Position(Row.THREE, Column.E),
                new Position(Row.ONE, Column.E),
                new Position(Row.ONE, Column.C),
                new Position(Row.TWO, Column.C)
        );
    }
}
