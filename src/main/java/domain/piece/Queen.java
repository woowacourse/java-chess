package domain.piece;

import domain.Player;
import domain.piece.Piece;
import domain.piece.PieceSymbol;
import domain.position.Position;
import java.util.List;

public class Queen extends Piece {

    public Queen(final Player player) {
        super(player, PieceSymbol.Queen);
    }

    @Override
    public List<Position> availableMovePositions(Position currentPosition) {
        return null;
    }
}
