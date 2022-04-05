package refactorChess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import refactorChess.domain.piece.PieceType;

class ChessBoardTest {

    @Test
    @DisplayName("체스 판을 생성할 수 있다.")
    void createChessBoard() {
        final ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.initChessBoard());

        assertThat(chessBoard.getBoard()).hasSize(64);
    }

    @Test
    @DisplayName("체스 판의 기물을 한 칸 이동할 수 있다.")
    void moveChessBoard() {
        final ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.initChessBoard());
        final Position from = Position.valueOf("a2");
        final Position to = Position.valueOf("a3");

        chessBoard.move(from, to);

        assertThat(chessBoard.findByPiece(to).getPieceType()).isEqualTo(PieceType.PAWN);
    }
}
