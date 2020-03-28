package chess.domain.piece;

import chess.domain.position.Position;

import java.util.List;

public class Knight extends Piece {
    public Knight(char representation, Position position) {
        super(representation, position);
    }

    @Override
    public List<Position> getPossiblePositions() {
        return null;
    }
}
