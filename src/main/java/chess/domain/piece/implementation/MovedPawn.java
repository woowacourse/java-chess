package chess.domain.piece.implementation;

import chess.domain.board.BoardState;
import chess.domain.direction.MovingDirection;
import chess.domain.piece.Pawn;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class MovedPawn extends Pawn {
    private MovedPawn(Position position, Team team) {
        super(position, team);
    }

    public static MovedPawn of(Position position, Team team) {
        return new MovedPawn(position, team);
    }

    @Override
    protected List<Position> movePositions(BoardState boardState) {
        MovingDirection moveDirection = MOVING_DIRECTION_BY_TEAM.get(team);
        List<Position> positions = new ArrayList<>();
        Position startPosition = position;
        if (startPosition.canMove(moveDirection)) {
            startPosition = startPosition.moveByDirection(moveDirection);
            if (boardState.canMove(startPosition)) {
                positions.add(startPosition);
            }
        }
        return positions;
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new MovedPawn(target, team);
    }
}
