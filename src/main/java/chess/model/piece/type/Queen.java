package chess.model.piece.type;

import chess.model.piece.Camp;
import chess.model.piece.Direction;
import chess.model.piece.Piece;
import chess.model.position.Distance;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> availableDirections = new ArrayList<>();

    static {
        availableDirections.addAll(Direction.DIAGONAL);
        availableDirections.addAll(Direction.ORTHOGONAL);
    }

    public Queen(final Camp camp) {
        super(camp);
    }

    @Override
    protected boolean isAvailableDirection(final Distance distance) {
        return availableDirections.stream()
                .anyMatch(distance::matchByDirection);
    }
}
