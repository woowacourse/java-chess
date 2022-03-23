package domain.piece;

import domain.Player;
import domain.position.Position;
import java.util.List;

public class King extends Piece {

    public King(final Player player) {
        super(player, PieceSymbol.King);
    }

    @Override
    public List<Position> availableMovePositions(Position currentPosition) {
        return null;
    }
}
