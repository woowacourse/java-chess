package model.piece.state;

import java.util.List;
import java.util.Set;
import model.Position;
import model.piece.Color;

public final class Square extends Role {
    public Square(){
        super(Color.UN_COLORED, List.of());
    }
    @Override
    public Set<Position> possiblePositions(Position position) {
        return Set.of();
    }
}
