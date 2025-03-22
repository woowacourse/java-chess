package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.List;
import java.util.Map;

public abstract class Piece {

    Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Position beforePosition, Position afterPosition);

    public abstract List<Movement> getMovements(Position beforePosition, Position afterPosition);

    public abstract boolean canMoveWithPieces(Map<Piece,Boolean> pieces);

    public Color getColor() {
        return color;
    }
}
