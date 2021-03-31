package chess.domain.piece.direction;

import chess.domain.Position;
import chess.domain.TeamColor;

import java.util.List;

public class PawnDirections extends PieceDirections {

    public PawnDirections(TeamColor teamColor) {
        super(false, Direction.forwardDirection(teamColor), Direction.forwardDiagonal(teamColor));
    }

    public void additionalMovePosition(Position currentPosition, List<Position> existPiecePositions, List<Position> movablePositions) {
        Direction moveDirection = moveDirections().get(0);
        if (currentPosition.invalidGo(moveDirection)) {
            return;
        }
        Position nextOneStep = currentPosition.go(moveDirection);
        if (nextOneStep.invalidGo(moveDirection)) {
            return;
        }
        Position nextTwoStep = nextOneStep.go(moveDirection);
        if (existPiecePositions.contains(nextTwoStep)) {
            return;
        }
        movablePositions.add(nextTwoStep);
    }
}
