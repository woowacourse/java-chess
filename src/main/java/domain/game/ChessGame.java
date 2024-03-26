package domain.game;

import domain.chessboard.ChessBoard;
import domain.chessboard.Row;
import domain.coordinate.Coordinate;
import domain.piece.Color;
import java.util.List;

public class ChessGame {

    private static final Color DEFAULT_START_COLOR = Color.WHITE;

    private final ChessBoard chessBoard;

    private Color movablePieceColor;

    public ChessGame() {
        this.chessBoard = new ChessBoard();
        movablePieceColor = DEFAULT_START_COLOR;
    }

    public void startTurn(Coordinate start, Coordinate destination) {
        chessBoard.movePiece(start, destination, movablePieceColor);
        changeTurn();
    }

    private void changeTurn() {
        if (movablePieceColor == Color.WHITE) {
            movablePieceColor = Color.BLACK;
            return;
        }
        movablePieceColor = Color.WHITE;
    }

    public List<Row> getCurrentBoard() {
        return chessBoard.getBoard();
    }
}
