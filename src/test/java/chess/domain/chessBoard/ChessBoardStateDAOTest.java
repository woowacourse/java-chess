package chess.domain.chessBoard;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.PieceColor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardStateDAOTest {

    @Test
    void ChessBoardState_initialState_returnPlayerColorBlackAndCaughtKingFalse() {
        ChessBoardState chessBoardState = ChessBoardState.of();
        assertThat(chessBoardState.isCaughtKing()).isFalse();
        assertThat(chessBoardState.getPlayerTurnState()).isEqualTo(PieceColor.WHITE);
    }

    @Test
    void playerTurnChange() {
        ChessBoardState chessBoardState = ChessBoardState.of();
        chessBoardState.playerTurnChange();

        assertThat(chessBoardState.getPlayerTurnState()).isEqualTo(PieceColor.BLACK);
    }

    @Test
    void checkCaughtPieceIsKing() {
        ChessBoardState chessBoardState = ChessBoardState.of();
        ChessPiece chessPiece = new King(PieceColor.BLACK);
        chessBoardState.caughtPieceIsKing(chessPiece);

        assertThat(chessBoardState.isCaughtKing()).isTrue();
    }
}