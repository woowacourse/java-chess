package chess.domain.piece.direction;

import chess.domain.Position;
import chess.domain.TeamColor;

import java.util.List;
import java.util.Optional;

public class PawnDirections extends Directions {

    public PawnDirections(TeamColor teamColor) {
        super(false, Direction.forwardDirection(teamColor), Direction.forwardDiagonal(teamColor));
    }

    public Optional<Position> additionalMovePosition(Position currentPosition, List<Position> existPiecePositions) {
        Direction moveDirection = moveDirections().get(0);
        Position nextOneStep = currentPosition.go(moveDirection);
        Position nextTwoStep = nextOneStep.go(moveDirection);

        if (existPiecePositions.contains(nextTwoStep)) {
            return Optional.empty();
        }
        return Optional.of(nextTwoStep);
    }
}
