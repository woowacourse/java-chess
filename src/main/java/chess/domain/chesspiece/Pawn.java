package chess.domain.chesspiece;

import chess.domain.position.Position;

import static chess.domain.chesspiece.Role.*;

public class Pawn extends Piece {

    public Pawn(Team team) {
        super(team);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        if (!isMovable(source, target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    private boolean isMovable(Position source, Position target) {
        return moveForwardTwice(source, target)
                || moveForward(source, target)
                || attack(source, target);
    }

    private boolean moveForwardTwice(Position source, Position target) {
        int columnDistance = source.calculatePawnColumnDistance(target, team);
        return source.isPawnStartPosition(team) && source.isSameRow(target) && columnDistance == 2;
    }

    private boolean moveForward(Position source, Position target) {
        int columnDistance = source.calculatePawnColumnDistance(target, team);
        return source.isSameRow(target) && columnDistance == 1;
    }

    private boolean attack(Position source, Position target) {
        int rowDistance = source.calculateRowDistance(target);
        int colDistance = source.calculatePawnColumnDistance(target, team);
        return rowDistance == 1 && colDistance == 1;
    }

    @Override
    public Role getRole() {
        if (team.isWhite()) {
            return WHITE_PAWN;
        }
        return BLACK_PAWN;
    }
}
