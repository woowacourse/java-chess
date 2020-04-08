package chess.domain.chessBoard;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.King;
import chess.domain.chessPiece.pieceType.PieceColor;

public class ChessBoardState {
    private boolean caughtKing;
    private PieceColor playerTurnState;

    private ChessBoardState() {
        this.caughtKing = false;
        this.playerTurnState = PieceColor.WHITE;
    }

    private ChessBoardState(PieceColor pieceColor) {
        this.caughtKing = false;
        this.playerTurnState = pieceColor;
    }

    public static ChessBoardState of() {
        return new ChessBoardState();
    }

    public static ChessBoardState of(PieceColor pieceColor) {
        return new ChessBoardState(pieceColor);
    }


    public void playerTurnChange() {
        this.playerTurnState = playerTurnState.getOppositeColor();
    }

    public void checkCaughtPieceIsKing(ChessPiece chessPiece) {
        if (chessPiece instanceof King) {
            this.caughtKing = true;
        }
    }

    public void validatePlayerTurn(ChessPiece sourceChessPiece) {
        this.playerTurnState.validatePlayerTurn(sourceChessPiece);
    }

    public boolean isCaughtKing() {
        return this.caughtKing;
    }

    public PieceColor getPlayerTurnState() {
        return this.playerTurnState;
    }
}
