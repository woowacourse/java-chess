package chess;

import chess.piece.ChessPiece;
import chess.piece.Empty;
import chess.piece.Shape;
import chess.piece.Side;
import chess.position.MovablePosition;
import chess.position.Position;

public class ChessGame {

    private static final String OUT_OF_CHESS_BOUND_ERROR = "[ERROR] 해당 위치로 움직일 수 없습니다.";

    private final ChessBoard chessBoard;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void moveChessPiece(Position sourcePosition, Position targetPosition) {
        ChessPiece chessPiece = findChessPiece(sourcePosition);
        copyChessPiece(chessPiece, targetPosition);
        removeChessPiece(sourcePosition);
    }

    public ChessPiece findChessPiece(Position sourcePosition) {
        return chessBoard.getChessPieceByPosition(sourcePosition);
    }

    public void copyChessPiece(ChessPiece chessPiece, Position targetPosition) {
        chessBoard.getChessBoard().put(targetPosition, chessPiece);
    }

    public void removeChessPiece(Position sourcePosition) {
        chessBoard.getChessBoard().put(sourcePosition, new Empty(Shape.EMPTY, Side.EMPTY));
    }

    public boolean validateMovablePosition(Position targetPosition, MovablePosition movablePosition) {
        if (movablePosition.getMovablePosition().stream()
                .anyMatch(position -> position.equals(targetPosition))) {
            return true;
        }
        throw new IllegalArgumentException(OUT_OF_CHESS_BOUND_ERROR);
    }
}
