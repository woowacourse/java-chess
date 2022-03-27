package domain.piece;

import domain.Player;
import domain.directions.Direction;
import domain.directions.DirectionsGenerator;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class SpecificLocationPiece extends Piece {

    public SpecificLocationPiece(Player player, PieceSymbol pieceSymbol,
        DirectionsGenerator directionsGenerator) {
        super(player, pieceSymbol, directionsGenerator);
    }

    @Override
    protected List<Position> calculateAvailablePosition(Position source, Direction direction) {
        List<Position> positions = new ArrayList<>();
        if (checkOverRange(source, direction)) {
            positions.add(createDirectionPosition(source, direction));
        }
        return positions;
    }
}
