package chess.domain.piece;

import chess.domain.board.BoardSituation;
import chess.domain.direction.MovingDirection;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class MoveByDirectionPiece extends Piece {

    protected MoveByDirectionPiece(PieceType pieceType, Position position, Team team) {
        super(pieceType, position, team);
    }

    @Override
    public List<Position> getMovablePositions(BoardSituation boardSituation) {
        List<Position> positions = new ArrayList<>();
        for (MovingDirection movingDirection : getMovingDirections()) {
            positions.addAll(getMovableBy(movingDirection, boardSituation));
        }
        return positions;
    }

    private List<Position> getMovableBy(MovingDirection movingDirection, BoardSituation boardSituation) {
        List<Position> positions = new ArrayList<>();
        Position startPosition = position;
        while (startPosition.canMoveBy(movingDirection)) {
            startPosition = startPosition.moveByDirection(movingDirection);
            if (boardSituation.canMove(startPosition) || boardSituation.canAttack(startPosition, team)) {
                positions.add(startPosition);
            }
            if (!boardSituation.canMove(startPosition)) {
                break;
            }
        }
        return positions;
    }

    protected abstract List<MovingDirection> getMovingDirections();
}
