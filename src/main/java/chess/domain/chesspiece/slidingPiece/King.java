package chess.domain.chesspiece.slidingPiece;

import static chess.domain.chesspiece.Role.BLACK_KING;
import static chess.domain.chesspiece.Role.WHITE_KING;

import chess.domain.chesspiece.Role;
import chess.domain.chesspiece.Team;
import chess.domain.position.Position;

public class King extends SlidingPiece {

    public King(Team team) {
        super(team);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int fileDistance = source.calculateFileDistance(target);
        int columnDistance = source.calculateRankDistance(target);
        if (fileDistance != 1 && columnDistance != 1) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public Role getRole() {
        if (getTeam().isWhite()) {
            return WHITE_KING;
        }
        return BLACK_KING;
    }
}
