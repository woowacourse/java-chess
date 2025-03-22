package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;

import java.util.List;
import java.util.Map;

public class None extends Piece {

    public None() {
        super("-", Color.EMPTY, null, null);
    }

    @Override
    public List<List<Movement>> getMovement() {
        throw new IllegalArgumentException();
    }

    @Override
    public Piece move(Map<Position, Piece> board, Position position) {
        throw new IllegalArgumentException();
    }
}
