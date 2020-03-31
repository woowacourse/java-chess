package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.exception.IllegalMoveException;

import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    private static List<Direction> possibleDirections = Arrays.asList(Direction.NORTH, Direction.EAST, Direction.WEST,
            Direction.SOUTH, Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHEAST, Direction.SOUTHWEST);

    public King(Position position, Team team) {
        super(position, team);
        this.representation = 'K';
        this.score = 0;
    }

    protected void validateMove(Position destination) {
        Direction direction = position.calculateDirection(destination);
        if (!possibleDirections.contains(direction)) {
            throw new IllegalMoveException(ILLEGAL_MOVE);
        }
    }

    @Override
    public void validateDestination(Position destination, Piece destinationPiece, List<Piece> piecesInBetween) {
        validateNoObstacle(piecesInBetween);
    }
}
