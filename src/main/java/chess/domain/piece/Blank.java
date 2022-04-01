package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Map;

public class Blank extends Piece {

    public Blank() {
        super(Type.BLANK, Color.NONE);
    }

    @Override
    public boolean isMovablePosition(Position source, Position target, Map<Position, Piece> board) {
        return false;
    }
}
