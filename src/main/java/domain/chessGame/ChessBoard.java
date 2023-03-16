package domain.chessGame;

import domain.piece.Piece;
import domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public final class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = new HashMap<>(chessBoard);
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }
}
