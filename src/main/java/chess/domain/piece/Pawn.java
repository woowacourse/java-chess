package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.List;

public abstract class Pawn extends Piece {
    public Pawn(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    protected boolean isFirstMove(int initialPawnRow) {
        return position.getY() == initialPawnRow;
    }

    protected boolean isForwardDirection(Direction direction, Direction forwardDirection) {
        return direction == forwardDirection;
    }

    @Override
    public List<Position> getPossiblePositions(Board board) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : getDirections()) {
            Position nextPosition = direction.move(position);

            if (isForwardDirection(direction, team.getForwardDirection())) {
                if (isInBoardRange(nextPosition) && board.isBlank(nextPosition)) {
                    possiblePositions.add(nextPosition);
                }
                if (isFirstMove(team.getInitialPawnRow())) {
                    possiblePositions.add(direction.move(nextPosition));
                }
                continue;
            }

            if (isInBoardRange(nextPosition) && board.isOtherTeam(position, nextPosition)) {
                possiblePositions.add(nextPosition);
            }
        }
        return possiblePositions;
    }

    protected abstract List<Direction> getDirections();
}
