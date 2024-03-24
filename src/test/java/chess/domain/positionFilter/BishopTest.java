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

class BishopTest {
    /**
     * ........  8 (rank 8)
     * ........  7
     * ........  6
     * ........  5
     * ........  4
     * ....P...  3
     * .p......  2
     * ..b.....  1 (rank 1)
     *
     * abcdefgh
     */
    @Test
    @DisplayName("실제로 움직일 수 있는 위치를 모두 가져온다.")
    void generateMovablePositions() {
        Position position = new Position(Row.ONE, Column.C);
        Piece piece = new Piece(PieceType.BISHOP, Color.WHITE);
        Board board = new Board(
                Map.of(
                        position, piece,
                        new Position(Row.TWO, Column.B), new Piece(PieceType.WHITE_PAWN, Color.WHITE),
                        new Position(Row.THREE, Column.E), new Piece(PieceType.BLACK_PAWN, Color.BLACK)
                )
        );

        List<Position> movablePositions = new PositionsFilter().generateValidPositions(
                piece.generateAllDirectionPositions(position), piece, board);

        assertThat(movablePositions).containsExactlyInAnyOrder(
                new Position(Row.TWO, Column.D),
                new Position(Row.THREE, Column.E)
        );
    }
}
