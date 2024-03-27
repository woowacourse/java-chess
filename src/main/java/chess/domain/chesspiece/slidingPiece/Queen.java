package chess.domain.chesspiece.slidingPiece;

import static chess.domain.chesspiece.Role.BLACK_QUEEN;
import static chess.domain.chesspiece.Role.WHITE_QUEEN;

import chess.domain.chesspiece.Role;
import chess.domain.chesspiece.Team;
import chess.domain.position.Position;

public class Queen extends SlidingPiece {

    public Queen(Team team) {
        super(team);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int fileDistance = source.calculateFileDistance(target);
        int columnDistance = source.calculateRankDistance(target);
        if (!source.isSameFile(target) && !source.isSameRank(target)
                && fileDistance != columnDistance) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public Role getRole() {
        if (getTeam().isWhite()) {
            return WHITE_QUEEN;
        }
        return BLACK_QUEEN;
    }
}
