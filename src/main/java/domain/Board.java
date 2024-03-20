package domain;

import domain.position.Position;

import java.util.Collections;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> chessBoard;

    public Board(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }
}
