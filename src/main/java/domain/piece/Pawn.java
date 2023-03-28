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
    public List<Square> findRoutes(Square source, Square destination, Piece pieceOfDestination) {
        checkState(source);
        validatePawnMove(source, destination, pieceOfDestination);
        return findRoutes(source, destination);
    }

    private void validatePawnMove(Square source, Square destination, Piece pieceOfDestination) {
        if (isDiagonal(source, destination) && canNotKill(pieceOfDestination)) {
            throw new IllegalArgumentException("대각선으로 갈 수 없습니다.");
        }
        if (isLinear(source, destination) && pieceOfDestination.isNotBlank()) {
            throw new IllegalArgumentException("폰은 기물이 있으면 앞으로 갈 수 없습니다.");
        }
    }

    private boolean canNotKill(Piece pieceOfDestination) {
        return pieceOfDestination.isBlank() || isSameTeam(pieceOfDestination);
    }

    @Override
    protected List<Square> findRoutes(Square source, Square destination) {
        Direction direction = destination.calculateVector(source);
        state.validateDirection(direction);
        return getSquares(source, destination, direction);
    }

    public void checkState(Square source) {
        if (state.isMoved(source)) {
            state = state.next();
        }
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

    public boolean isLinear(Square source, Square destination) {
        return !isDiagonal(source, destination);
    }

    public boolean isDiagonal(Square source, Square destination) {
        Direction direction = source.calculateVector(destination);
        return Directions.Diagonal.contains(direction);
    }
}
