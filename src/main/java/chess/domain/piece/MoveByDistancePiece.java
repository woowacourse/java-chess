package chess.domain.piece;

import chess.domain.board.BoardSituation;
import chess.domain.direction.MovingDirection;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class MoveByDistancePiece extends Piece {

    protected MoveByDistancePiece(PieceType pieceType, Position position, Team team) {
        super(pieceType, position, team);
    }

    @Override
    public List<Position> getMovablePositions(BoardSituation boardSituation) {
        List<Position> positions = new ArrayList<>();
        for (MovingDirection movingDirection : getMovingDirections()) {
            addPositionBy(movingDirection, boardSituation, positions);
        }
        return positions;
    }

    private void addPositionBy(MovingDirection movingDirection, BoardSituation boardSituation, List<Position> positions) {
        Position targetPosition = position;
        if (targetPosition.canMoveBy(movingDirection)) {
            targetPosition = targetPosition.moveByDirection(movingDirection);
            if (boardSituation.canMove(targetPosition) || boardSituation.canAttack(targetPosition, team)) {
                positions.add(targetPosition);
            }
        }
    }

    protected abstract List<MovingDirection> getMovingDirections();
}
