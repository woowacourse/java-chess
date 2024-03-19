package chess;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoard;
import chess.domain.Piece;
import chess.domain.PieceWithColor;
import java.util.List;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    void create() {
        final ChessBoard chessBoard = ChessBoard.init();
        final List<List<PieceWithColor>> chessBoardDetail = chessBoard.getBoard();
        assertThat(chessBoardDetail.get(7)).containsExactly(
                new PieceWithColor(Piece.ROOK, PieceColor.BLACK),
                new PieceWithColor(Piece.NIGHT, PieceColor.BLACK),
                new PieceWithColor(Piece.BISHOP, PieceColor.BLACK),
                new PieceWithColor(Piece.QUEEN, PieceColor.BLACK),
                new PieceWithColor(Piece.KING, PieceColor.BLACK),
                new PieceWithColor(Piece.BISHOP, PieceColor.BLACK),
                new PieceWithColor(Piece.NIGHT, PieceColor.BLACK),
                new PieceWithColor(Piece.ROOK, PieceColor.BLACK)
        );

    }
}
