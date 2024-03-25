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

class RookTest {

    /**
     * ........  8 (rank 8)
     * ........  7
     * ...B....  6
     * .n.Rr...  5
     * ...r....  4
     * ........  3
     * ........  2
     * ........  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("실제로 움직일 수 있는 위치를 모두 가져온다.")
    void generateMovablePositions() {
        Position position = new Position(Row.FIVE, Column.D);
        Piece piece = new Piece(PieceType.ROOK, Color.BLACK);
        Board board = new Board(
                Map.of(
                        position, piece,
                        new Position(Row.FOUR, Column.D), new Piece(PieceType.ROOK, Color.WHITE),
                        new Position(Row.FIVE, Column.E), new Piece(PieceType.ROOK, Color.WHITE),
                        new Position(Row.FIVE, Column.B), new Piece(PieceType.KNIGHT, Color.WHITE),
                        new Position(Row.SIX, Column.D), new Piece(PieceType.BISHOP, Color.BLACK)
                )
        );

        List<Position> movablePositions = new PositionsFilter().generateValidPositions(
                piece.generateAllDirectionPositions(position), piece, board);

        assertThat(movablePositions).containsExactlyInAnyOrder(
                new Position(Row.FOUR, Column.D),
                new Position(Row.FIVE, Column.E),
                new Position(Row.FIVE, Column.B),
                new Position(Row.FIVE, Column.C)
        );
    }

}
