package chess.domain;

import chess.domain.pieces.Color;
import chess.domain.pieces.King;
import chess.domain.pieces.Piece;

import java.util.List;
import java.util.Map;

public class ChessGame {

    private boolean isEnd;
    private Color colorOfTurn;
    private ChessBoard chessBoard;

    public ChessGame() {
        this.isEnd = false;
        this.colorOfTurn = Color.WHITE;
        chessBoard = new ChessBoard();
    }

    public ChessGame(Color currentTurn, Map<Point, Piece> points) {
        this.isEnd = false;
        this.colorOfTurn = currentTurn;
        chessBoard = new ChessBoard(points);
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

    public Map<Point, Piece> getBoard() {
        return chessBoard.getPoints();
    }

    public Piece getPiece(Point point) {
        return chessBoard.getPiece(point);
    }

    public Color getColor() {
        return this.colorOfTurn;
    }
}
