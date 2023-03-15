package chess;

import chess.piece.ChessPiece;
import chess.piece.Empty;

import java.util.List;

public class ChessGame {

    private final ChessBoard chessBoard;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void moveChessPiece(List<Integer> sourcePosition, List<Integer> targetPosition) {
        ChessPiece chessPiece = findChessPiece(sourcePosition);
        copyChessPiece(chessPiece, targetPosition);
        removeChessPiece(sourcePosition);
    }


    public ChessPiece findChessPiece(List<Integer> sourcePosition) {
        return chessBoard.getChessPieceByPosition(sourcePosition);
    }

    public void copyChessPiece(ChessPiece chessPiece, List<Integer> targetPosition) {
        chessBoard.getChessBoard().put(targetPosition, chessPiece);
    }

    public void removeChessPiece(List<Integer> sourcePosition) {
        chessBoard.getChessBoard().put(sourcePosition, new Empty(Side.BLANK));
    }
}
