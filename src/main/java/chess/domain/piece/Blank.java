package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public class Blank extends Piece {
    public Blank(char representation, Position position) {
        super(representation, position);
    }

    @Override
    public List<Position> getPossiblePositions() {
        return null;
    }
}
