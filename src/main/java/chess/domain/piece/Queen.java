package chess.domain.piece;

import chess.domain.game.Position;

import static chess.domain.piece.MoveStrategy.QUEEN;

public class Queen extends Piece {

    public Queen(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target, Team team) {
        if (this.isNotSameTeam(team)) {
            return QUEEN.isMovable(source, target);
        }
        throw new IllegalArgumentException("[ERROR] 타겟 위치에 같은 팀 기물이 있습니다.");
    }

    @Override
    protected int calculateCount(int rankDiff, int fileDiff) {
        if (Math.abs(fileDiff) == Math.abs(rankDiff)) {
            return Math.abs(fileDiff);
        }
        return Math.abs(fileDiff + rankDiff);
    }

    @Override
    public PieceType type() {
        return PieceType.QUEEN;
    }
}
