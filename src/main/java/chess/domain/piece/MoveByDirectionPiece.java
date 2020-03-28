package chess.domain.piece;

import chess.domain.board.BoardState;
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
    public List<Position> getMovablePositions(BoardState boardState) {
        List<Position> positions = new ArrayList<>();
        for (MovingDirection movingDirection : getMovingDirections()) {
            positions.addAll(getMovableBy(movingDirection, boardState));
        }
        return positions;
    }

    private List<Position> getMovableBy(MovingDirection movingDirection, BoardState boardState) {
        List<Position> positions = new ArrayList<>();
        Position startPosition = position;
        while (startPosition.canMoveBy(movingDirection)) {
            startPosition = startPosition.moveByDirection(movingDirection);
            if (boardState.canMove(startPosition) || boardState.canAttack(startPosition, team)) {
                positions.add(startPosition);
            }
            if (!boardState.canMove(startPosition)) {
                break;
            }
        }
        return positions;
    }

    private void addIfMoveOrAttack(BoardState boardState, List<Position> positions, Position startPathPosition) {
        if (boardState.canMove(startPathPosition) || boardState.canAttack(startPathPosition, team)) {
            positions.add(startPathPosition);
        }
    }

    protected abstract List<MovingDirection> getMovingDirections();
}
