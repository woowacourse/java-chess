package model;

import java.util.Map;
import model.piece.Piece;

public class ChessBoard {
    private final Map<Position, Piece> chessBoard;

    public ChessBoard() {
        this.chessBoard = ChessBoardFactory.create();
    }

    public Map<Position, Piece> getChessBoard() {
        return Map.copyOf(chessBoard);
    }
}
