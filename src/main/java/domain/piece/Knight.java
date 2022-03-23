package domain.piece;

import domain.Player;
import domain.directions.BishopDirections;
import domain.position.Position;
import java.util.List;

public class Knight extends Piece {

    public Knight(final Player player) {
        super(player, PieceSymbol.Knight, new BishopDirections());
    }

    @Override
    public List<Position> availableMovePositions(Position currentPosition) {
        return null;
    }
}
