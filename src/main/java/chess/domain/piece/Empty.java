package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.domain.piece.Type.EMPTY;

public class Empty implements Piece{
    @Override
    public boolean isBlack() {
        return false;
    }

    @Override
    public boolean isSameColor(Color color) {
        return false;
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
    public String identifyType() {
        return EMPTY.name();
    }
}
