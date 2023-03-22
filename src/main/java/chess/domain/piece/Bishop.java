package chess.domain.piece;

import chess.domain.game.Position;

import static chess.domain.piece.MoveStrategy.BISHOP;

public class Bishop extends Piece {

    public Bishop(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target, Team team) {
        if (this.isNotSameTeam(team)) {
            return BISHOP.isMovable(source, target);
        }
        throw new IllegalArgumentException("[ERROR] 타겟 위치에 같은 팀 기물이 있습니다.");
    }

    @Override
    protected int calculateCount(int fileDiff, int rankDiff) {
        return Math.abs(fileDiff);
    }
}
