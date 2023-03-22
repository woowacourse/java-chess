package chess.domain.piece;

import chess.domain.game.Position;

import java.util.List;

import static chess.domain.piece.MoveStrategy.KNIGHT;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team);
    }

    @Override
    public boolean isMovable(Position source, Position target, Team team) {
        if (this.isNotSameTeam(team)) {
            return KNIGHT.isMovable(source, target);
        }
        throw new IllegalArgumentException("[ERROR] 타겟 위치에 같은 팀 기물이 있습니다.");
    }

    @Override
    public List<Position> findPath(Position source, Position target) {
        return List.of(target);
    }

    @Override
    protected int calculateCount(int fileDiff, int rankDiff) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PieceType type() {
        return PieceType.KNIGHT;
    }
}
