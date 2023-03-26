package chess;

import chess.database.ChessGameDao;
import chess.piece.ChessPiece;
import chess.piece.Empty;
import chess.piece.Shape;
import chess.piece.Side;
import chess.position.MovablePosition;
import chess.position.Position;

public class ChessGame {

    private static final String OUT_OF_CHESS_BOUND_ERROR = "[ERROR] 해당 위치로 움직일 수 없습니다.";

    private final int gameIdx;
    private final ChessBoard chessBoard;

    public ChessGame(ChessBoard chessBoard) {
        ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.addGame();
        this.gameIdx = chessGameDao.findLastInsertGame();
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

    public boolean isGameEnd(ChessBoard chessBoard) {
        return chessBoard.checkKingIsDead();
    }

    public double takeScore(Side side) {
        return chessBoard.calculateScore(side);
    }

//    public double takeWinner(double whiu)
}
