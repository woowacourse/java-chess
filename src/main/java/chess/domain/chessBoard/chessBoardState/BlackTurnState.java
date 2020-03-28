package chess.domain.chessBoard.chessBoardState;

import chess.domain.chessPiece.pieceType.PieceColor;

public class BlackTurnState extends PlayerTurnState {

    public BlackTurnState() {
        playerColor = PieceColor.BLACK;
    }

    @Override
    public PlayerTurnState changePlayerTurn() {
        return new WhiteTurnState();
    }

    @Override
    public PieceColor getPlayerColor() {
        return playerColor;
    }
}
