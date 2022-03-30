package domain.piece;

import domain.Player;
import domain.direction.Direction;
import domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class SpecificLocationPiece extends Piece {

    public SpecificLocationPiece(Player player, PieceSymbol pieceSymbol) {
        super(player, pieceSymbol);
    }

    @Override
    protected List<Position> calculateAvailablePosition(Position source, Direction direction) {
        List<Position> positions = new ArrayList<>();
        if (checkOverRange(source, direction)) {
            positions.add(createPositionByDirection(source, direction));
        }
        return positions;
    }
}
