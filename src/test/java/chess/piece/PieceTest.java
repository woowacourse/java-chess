package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Board;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    void 기물을_특정_위치로_움직인다() {
        Board board = new Board();
        Position position = new Position(Row.FIVE, Column.D);
        board.put(new Knight(position, board, Color.BLACK));

        Piece piece = board.findPiece(position, Color.BLACK);
        Position targetPosition = new Position(Row.SEVEN, Column.C);
        piece.move(targetPosition);

        assertThat(piece.getPosition()).isEqualTo(targetPosition);
    }
}
