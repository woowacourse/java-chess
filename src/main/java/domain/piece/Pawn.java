package domain.piece;

import domain.DirectionsGenerator;
import domain.Player;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private List<Position> positions = new ArrayList<>();

    public Pawn(final Player player, final DirectionsGenerator directionsGenerator) {
        super(player, PieceSymbol.Pawn, directionsGenerator);
    }

    @Override
    public List<Position> availableMovePositions(Position currentPosition) {
        return null;
    }
}
