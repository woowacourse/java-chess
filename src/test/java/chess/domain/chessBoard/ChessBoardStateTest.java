package chess.domain.chessBoard;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.PieceColor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardStateTest {

    @Test
    void ChessBoardState_initialState_returnPlayerColorBlackAndCaughtKingFalse() {
        ChessBoardState chessBoardState = ChessBoardState.initialState();
        assertThat(chessBoardState.isCaughtKing()).isFalse();
        assertThat(chessBoardState.getPlayerTurnState()).isEqualTo(PieceColor.BLACK);
    }

    @Test
    void playerTurnChange() {
        ChessBoardState chessBoardState = ChessBoardState.initialState();
        chessBoardState.playerTurnChange();

        assertThat(chessBoardState.getPlayerTurnState()).isEqualTo(PieceColor.WHITE);
    }

    @Test
    void checkCaughtPieceIsKing() {
        ChessBoardState chessBoardState = ChessBoardState.initialState();
        ChessPiece chessPiece = new King(PieceColor.BLACK);
        chessBoardState.checkCaughtPieceIsKing(chessPiece);

        assertThat(chessBoardState.isCaughtKing()).isTrue();
    }
}