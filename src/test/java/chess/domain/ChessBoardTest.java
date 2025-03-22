package chess.domain;

import static chess.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @DisplayName("체스판 초기화")
    @Test
    void check_ChessBoardInitialize() {
        // given
        ChessBoard board = ChessBoard.createInitialBoard();

        // when
        Piece piece = board.findPieceBy(A1);

        // then
        assertThat(piece).isInstanceOf(Rook.class);
    }

    @DisplayName("체스 움직임")
    @Test
    void move_onBoard() {
        // given
        ChessBoard board = ChessBoard.createInitialBoard();
        Piece piece = board.findPieceBy(B1);

        // when
        board.move(B1, C3);

        // then
        Piece movedPiece = board.findPieceBy(C3);
        assertThat(movedPiece).isEqualTo(piece);
        assertThat(board.findPieceBy(B1)).isEqualTo(EmptyPiece.getInstance());
    }
}