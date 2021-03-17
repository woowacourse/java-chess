package chess.domain.piece;

import chess.domain.board.Horizontal;
import chess.domain.board.Location;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private static final String BISHOP_NAME = "B";
    private static final int POSITIVE = 1;
    private static final int NEGATIVE = -1;

    public Bishop(Team team) {
        super(BISHOP_NAME, team);
    }

    @Override
    public List<Location> movableLocations(Location target) {
        List<Location> movableLocations = new ArrayList<>();

        movableLocations.addAll(diagonal(target, POSITIVE, POSITIVE));
        movableLocations.addAll(diagonal(target, NEGATIVE, POSITIVE));
        movableLocations.addAll(diagonal(target, NEGATIVE, NEGATIVE));
        movableLocations.addAll(diagonal(target, POSITIVE, NEGATIVE));

        return movableLocations;
    }

    private List<Location> diagonal(Location target, int horizontalDirection, int verticalDirection) {
        List<Location> result = new ArrayList<>();
        int horizontalWeight = target.getHorizontal().getWeight();
        int verticalWeight = target.getVertical().getWeight();

        while (horizontalWeight > 1 && horizontalWeight < 8 && verticalWeight > 1 && verticalWeight < 8) {
            horizontalWeight += horizontalDirection;
            verticalWeight += verticalDirection;

            result.add(
                    Location.of(Horizontal.findFromWeight(horizontalWeight), Vertical.findFromWeight(verticalWeight))
            );
        }
        return result;
    }
}
