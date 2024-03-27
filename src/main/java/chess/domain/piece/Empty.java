package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.domain.piece.Type.EMPTY;

public class Empty extends Piece {
    public Empty() {
        super(Color.NONE);
    }

    @Override
    public boolean canMove(Position source, Position target, Piece piece) {
        return false;
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        return new ArrayList<>();
    }

    @Override
    public Type identifyType() {
        return EMPTY;
    }
}
