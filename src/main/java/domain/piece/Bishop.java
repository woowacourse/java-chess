package domain.piece;

import domain.Player;
import domain.position.Position;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(final Player player) {
        super(player, PieceSymbol.Bishop);
    }

    @Override
    public List<Position> availableMovePositions(Position currentPosition) {
        return null;
    }
}
