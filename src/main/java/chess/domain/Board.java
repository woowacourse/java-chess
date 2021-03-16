package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> chessBoard = new HashMap<>();

    public void initializePawn() {

    }

    public Map<Position, Piece> unwrap() {
        return chessBoard;
    }
}
