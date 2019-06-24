package chess.domain;

import chess.domain.pieces.Color;

import java.util.List;

public class ChessGame {

    private Color colorOfTurn;
    private ChessBoard chessBoard;

    public ChessGame() {
        this.colorOfTurn = Color.WHITE;
        chessBoard = new ChessBoard();
    }

    private void changeColorOfTurn() {
        this.colorOfTurn = (this.colorOfTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    public void play(Point source, Point target) {
        chessBoard.checkSourceAndTarget(source, target, colorOfTurn);
        List<Point> path = chessBoard.makePath(source, target);
        chessBoard.checkPath(path);
        chessBoard.updatePieceLocation(source, target);
        changeColorOfTurn();
    }
}
