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
    public List<Square> findRoutes(Square src, Square dest) {
        Direction vector = dest.calculateVector(src);
        state.validateDirection(vector);

        return getSquares(src, dest, vector);
    }

    private List<Square> getSquares(Square src, Square dest, Direction vector) {
        if (vector.equals(Direction.TOP_TOP)) {
            return List.of(src.add(Direction.TOP), src.add(Direction.TOP_TOP));
        }
        if (vector.equals(Direction.BOTTOM_BOTTOM)) {
            return List.of(src.add(Direction.BOTTOM), src.add(Direction.BOTTOM_BOTTOM));
        }
        return List.of(dest);
    }

    public void changeState() {
        state = state.next();
    }

    public boolean isLinear(Square src, Square dest) {
        return !isDiagonal(src, dest);
    }

    public boolean isDiagonal(Square src, Square dest) {
        Direction vector = src.calculateVector(dest);
        return Directions.Diagonal.contains(vector);
    }
}
