import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @DisplayName("Board를 생성하면 Piece들의 위치가 초기 위치여아한다.")
    @Test
    void startPieceLocation() {
        Board board = new Board();

        List<Piece> pieces = board.pieces;

        assertThat(pieces).containsExactlyInAnyOrder(
                new Rook(Position.of(1, 1), Team.WHITE),
                new Rook(Position.of(1, 8), Team.WHITE),
                new Knight(Position.of(1, 2), Team.WHITE),
                new Knight(Position.of(1, 7), Team.WHITE),
                new Bishop(Position.of(1, 3), Team.WHITE),
                new Bishop(Position.of(1, 6), Team.WHITE),
                new Queen(Position.of(1, 4), Team.WHITE),
                new King(Position.of(1, 5), Team.WHITE),

                new Pawn(Position.of(2, 1), Team.WHITE),
                new Pawn(Position.of(2, 2), Team.WHITE),
                new Pawn(Position.of(2, 3), Team.WHITE),
                new Pawn(Position.of(2, 4), Team.WHITE),
                new Pawn(Position.of(2, 5), Team.WHITE),
                new Pawn(Position.of(2, 6), Team.WHITE),
                new Pawn(Position.of(2, 7), Team.WHITE),
                new Pawn(Position.of(2, 8), Team.WHITE),

                new Pawn(Position.of(7, 1), Team.BLACK),
                new Pawn(Position.of(7, 2), Team.BLACK),
                new Pawn(Position.of(7, 3), Team.BLACK),
                new Pawn(Position.of(7, 4), Team.BLACK),
                new Pawn(Position.of(7, 5), Team.BLACK),
                new Pawn(Position.of(7, 6), Team.BLACK),
                new Pawn(Position.of(7, 7), Team.BLACK),
                new Pawn(Position.of(7, 8), Team.BLACK),

                new Rook(Position.of(8, 1), Team.BLACK),
                new Rook(Position.of(8, 8), Team.BLACK),
                new Knight(Position.of(8, 2), Team.BLACK),
                new Knight(Position.of(8, 7), Team.BLACK),
                new Bishop(Position.of(8, 3), Team.BLACK),
                new Bishop(Position.of(8, 6), Team.BLACK),
                new Queen(Position.of(8, 4), Team.BLACK),
                new King(Position.of(8, 5), Team.BLACK)
        );
    }
}
