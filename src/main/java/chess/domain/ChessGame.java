package chess.domain;

import chess.domain.pieces.Color;
import chess.domain.pieces.King;
import chess.domain.pieces.Piece;

import java.util.List;

public class ChessGame {

    private boolean isEnd;
    private Color colorOfTurn;
    private ChessBoard chessBoard;

    public ChessGame() {
        this.isEnd = false;
        this.colorOfTurn = Color.WHITE;
        chessBoard = new ChessBoard();
    }

    public void play(Point source, Point target) {
        chessBoard.checkSourceAndTarget(source, target, colorOfTurn);
        List<Point> path = chessBoard.makePath(source, target);
        chessBoard.checkPath(path);

        if (chessBoard.hasPiece(target, new King(changeColorOfTurn()))) {
            isEnd = true;
        }

        chessBoard.updatePieceLocation(source, target);
        this.colorOfTurn = changeColorOfTurn();
    }

    private Color changeColorOfTurn() {
        return (this.colorOfTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public boolean hasPiece(Point point, Piece piece) {
        return chessBoard.hasPiece(point, piece);
    }
}
