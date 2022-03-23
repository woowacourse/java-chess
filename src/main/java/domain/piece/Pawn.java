package domain.piece;

import domain.Player;
import domain.position.Position;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(final Player player) {
        super(player, PieceSymbol.Pawn);
    }

    @Override
    public List<Position> availableMovePositions(Position currentPosition) {
        return null;
    }
}
