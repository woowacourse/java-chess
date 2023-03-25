package domain.piece;

import domain.Square;
import java.util.List;

public class Pawn extends Piece {

    private PawnState state;

    public Pawn(TeamColor teamColor) {
        super(teamColor, PieceInfo.PAWN);
        state = PawnState.init(teamColor);
    }

    @Override
    public List<Square> findRoutes(Square source, Square destination) {
        Direction direction = destination.calculateVector(source);
        state.validateDirection(direction);

        return getSquares(source, destination, direction);
    }

    private List<Square> getSquares(Square source, Square destination, Direction direction) {
        if (direction.equals(Direction.TOP_TOP)) {
            return List.of(source.add(Direction.TOP), source.add(Direction.TOP_TOP));
        }
        if (direction.equals(Direction.BOTTOM_BOTTOM)) {
            return List.of(source.add(Direction.BOTTOM), source.add(Direction.BOTTOM_BOTTOM));
        }
        return List.of(destination);
    }

    public void changeState() {
        state = state.next();
    }

    public boolean isLinear(Square source, Square destination) {
        return !isDiagonal(source, destination);
    }

    public boolean isDiagonal(Square source, Square destination) {
        Direction direction = source.calculateVector(destination);
        return Directions.Diagonal.contains(direction);
    }
}
