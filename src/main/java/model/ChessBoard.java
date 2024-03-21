package model;

import java.util.Map;
import model.piece.PieceHolder;
import model.position.Position;

public class ChessBoard {
    private final Map<Position, PieceHolder> chessBoard;

    public ChessBoard() {
        this.chessBoard = ChessBoardFactory.create();
    }

    public Map<Position, PieceHolder> getChessBoard() {
        return Map.copyOf(chessBoard);
    }
}
