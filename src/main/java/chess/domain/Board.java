package chess.domain;

import chess.domain.piece.Piece;
import java.util.Map;

public class Board {

    private final Map<Point, Piece> board;

    public Board(Map<Point, Piece> board) {
        this.board = board;
    }
}
