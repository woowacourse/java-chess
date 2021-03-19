package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

import java.util.List;

import static chess.domain.Direction.forwardDiagonal;
import static chess.domain.Direction.forwardDirection;

public class Pawn extends Piece {

    public Pawn(TeamColor teamColor, Position position) {
        super("p", teamColor, Score.from(1), forwardDirection(teamColor), forwardDiagonal(teamColor),
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

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    private void addAdditionalMove(Direction moveDirection, List<Position> existPiecePositions, Position currentPosition) {
        if (notMoved()) {
            currentPosition = currentPosition.go(moveDirection);
        }
        if (existPiecePositions.contains(currentPosition)) return;
        movablePositions().add(currentPosition);
    }
}
