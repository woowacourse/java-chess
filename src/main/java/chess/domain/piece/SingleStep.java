package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.Direction;

import java.util.ArrayList;
import java.util.List;

public abstract class SingleStep extends Piece {
    public SingleStep(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    @Override
    public List<Position> getPossiblePositions(Board board) {
        List<Position> possiblePositions = new ArrayList<>();

        for (Direction direction : getDirections()) {
            Position nextPosition = direction.move(position);

            if (isInBoardRange(nextPosition) && board.isBlank(nextPosition)) {
                possiblePositions.add(nextPosition);
            }

            if (isInBoardRange(nextPosition) && board.isOtherTeam(position, nextPosition)) {
                possiblePositions.add(nextPosition);
            }
        }
        return possiblePositions;
    }

    protected abstract List<Direction> getDirections();
}
