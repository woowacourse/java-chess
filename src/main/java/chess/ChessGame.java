package chess;

import chess.piece.ChessPiece;
import chess.piece.Empty;

import java.util.List;

public class ChessGame {

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
        chessBoard.getChessBoard().put(sourcePosition, new Empty(Side.BLANK));
    }

    public void validateMovablePosition(Position targetPosition, List<Position> movablePosition) {
        movablePosition.stream()
                .filter(position -> position.equals(targetPosition))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 위치로는 이동할 수 없습니다."));
    }
}
