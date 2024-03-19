package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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

        assertAll(
                () -> assertThat(chessBoardDetail.get(0)).containsExactly(
                        new PieceWithColor(Piece.ROOK, PieceColor.WHITE),
                        new PieceWithColor(Piece.NIGHT, PieceColor.WHITE),
                        new PieceWithColor(Piece.BISHOP, PieceColor.WHITE),
                        new PieceWithColor(Piece.QUEEN, PieceColor.WHITE),
                        new PieceWithColor(Piece.KING, PieceColor.WHITE),
                        new PieceWithColor(Piece.BISHOP, PieceColor.WHITE),
                        new PieceWithColor(Piece.NIGHT, PieceColor.WHITE),
                        new PieceWithColor(Piece.ROOK, PieceColor.WHITE)
                ),
                () -> assertThat(chessBoardDetail.get(1)).containsExactly(
                        new PieceWithColor(Piece.PAWN, PieceColor.WHITE),
                        new PieceWithColor(Piece.PAWN, PieceColor.WHITE),
                        new PieceWithColor(Piece.PAWN, PieceColor.WHITE),
                        new PieceWithColor(Piece.PAWN, PieceColor.WHITE),
                        new PieceWithColor(Piece.PAWN, PieceColor.WHITE),
                        new PieceWithColor(Piece.PAWN, PieceColor.WHITE),
                        new PieceWithColor(Piece.PAWN, PieceColor.WHITE),
                        new PieceWithColor(Piece.PAWN, PieceColor.WHITE)
                ),
                () -> assertThat(chessBoardDetail.get(2)).containsExactly(
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY)
                ),
                () -> assertThat(chessBoardDetail.get(3)).containsExactly(
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY)
                ),
                () -> assertThat(chessBoardDetail.get(4)).containsExactly(
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY)
                ),
                () -> assertThat(chessBoardDetail.get(5)).containsExactly(
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY),
                        new PieceWithColor(Piece.EMPTY, PieceColor.EMPTY)
                ),
                () -> assertThat(chessBoardDetail.get(6)).containsExactly(
                        new PieceWithColor(Piece.PAWN, PieceColor.BLACK),
                        new PieceWithColor(Piece.PAWN, PieceColor.BLACK),
                        new PieceWithColor(Piece.PAWN, PieceColor.BLACK),
                        new PieceWithColor(Piece.PAWN, PieceColor.BLACK),
                        new PieceWithColor(Piece.PAWN, PieceColor.BLACK),
                        new PieceWithColor(Piece.PAWN, PieceColor.BLACK),
                        new PieceWithColor(Piece.PAWN, PieceColor.BLACK),
                        new PieceWithColor(Piece.PAWN, PieceColor.BLACK)
                ),
                () -> assertThat(chessBoardDetail.get(7)).containsExactly(
                        new PieceWithColor(Piece.ROOK, PieceColor.BLACK),
                        new PieceWithColor(Piece.NIGHT, PieceColor.BLACK),
                        new PieceWithColor(Piece.BISHOP, PieceColor.BLACK),
                        new PieceWithColor(Piece.QUEEN, PieceColor.BLACK),
                        new PieceWithColor(Piece.KING, PieceColor.BLACK),
                        new PieceWithColor(Piece.BISHOP, PieceColor.BLACK),
                        new PieceWithColor(Piece.NIGHT, PieceColor.BLACK),
                        new PieceWithColor(Piece.ROOK, PieceColor.BLACK)
                )
        );
    }
}
