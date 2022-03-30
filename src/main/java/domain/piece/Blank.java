package domain.piece;

import domain.Player;
import domain.direction.Direction;
import domain.position.Position;
import java.util.List;

public class Blank extends Piece {

    public Blank() {
        super(Player.NULL, PieceSymbol.NULL);
    }

    public Blank(final Player player) {
        super(player, PieceSymbol.NULL);
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
    protected List<Position> calculateAvailablePosition(Position source,
        Direction direction) {
        throw new IllegalArgumentException("[ERROR] 해당 위치는 비어있는 칸입니다.");
    }
}
