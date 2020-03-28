package chess.domain.chessBoard.chessBoardState;

import chess.domain.chessPiece.pieceType.PieceColor;

public class WhiteTurnState extends PlayerTurnState {

    public WhiteTurnState() {
        playerColor = PieceColor.WHITE;
    }

    @Override
    public PlayerTurnState changePlayerTurn() {
        return new BlackTurnState();
    }

    @Override
    public PieceColor getPlayerColor() {
        return playerColor;
    }
}
