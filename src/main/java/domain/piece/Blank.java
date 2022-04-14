package domain.piece;

import domain.Player;
import domain.direction.Direction;
import domain.position.Position;
import java.util.List;

public class Blank extends Piece {

    public Blank() {
        super(Player.NULL, PieceSymbol.NULL);
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    protected List<Direction> getDirections() {
        throw new IllegalArgumentException("[ERROR] 해당 위치는 비어있는 칸입니다.");
    }

    @Override
    protected Direction direction(Position source, Position target) {
        return null;
    }

    @Override
    public double score(final boolean isSeveralPawn) {
        return 0;
    }
}
