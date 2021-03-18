package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.TeamColor;

import java.util.List;

import static chess.domain.Direction.forwardDiagonal;
import static chess.domain.Direction.forwardDirection;

public class Pawn extends Piece {

    public Pawn(TeamColor teamColor, Position position) {
        super("p", teamColor, forwardDirection(teamColor), forwardDiagonal(teamColor),
                false, position);
    }

    @Override
    protected void addPosition(Direction moveDirection, List<Position> existPiecePositions) {
        Position currentPosition = currentPosition();

        if (currentPosition.invalidGo(moveDirection)) return;
        currentPosition = currentPosition.go(moveDirection);

        if (existPiecePositions.contains(currentPosition)) return;
        movablePositions().add(currentPosition);
        addAdditionalMove(moveDirection, existPiecePositions, currentPosition);

    }

    private void addAdditionalMove(Direction moveDirection, List<Position> existPiecePositions, Position currentPosition) {
        if (notMoved()) {
            currentPosition = currentPosition.go(moveDirection);
        }
        if (existPiecePositions.contains(currentPosition)) return;
        movablePositions().add(currentPosition);
    }
}
