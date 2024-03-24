package chess.model.piece;

import chess.model.material.Color;
import chess.model.material.Type;
import chess.model.position.Position;

public class None extends Piece {

    public None(Type type, Color color) {
        super(type, color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return false;
    }
}
