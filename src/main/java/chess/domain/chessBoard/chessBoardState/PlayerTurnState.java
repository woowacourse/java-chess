package chess.domain.chessBoard.chessBoardState;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.PieceColor;

public abstract class PlayerTurnState {
    protected PieceColor playerColor;

    public abstract PlayerTurnState changePlayerTurn();

    public void validatePlayerTurn(ChessPiece chessPiece) {
        if (!chessPiece.isSamePieceColorWith(playerColor)) {
            throw new IllegalArgumentException("해당 플레이어 턴이 아닙니다.");
        }
    }

    public abstract PieceColor getPlayerColor();
}
