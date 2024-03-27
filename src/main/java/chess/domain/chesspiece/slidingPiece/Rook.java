package chess.domain.chesspiece.slidingPiece;

import static chess.domain.chesspiece.Role.BLACK_ROOK;
import static chess.domain.chesspiece.Role.WHITE_ROOK;

import chess.domain.chesspiece.Role;
import chess.domain.chesspiece.Team;
import chess.domain.position.Position;

public class Rook extends SlidingPiece {

    public Rook(Team team) {
        super(team);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        if (!source.isSameRank(target) && !source.isSameFile(target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public Role getRole() {
        if (getTeam().isWhite()) {
            return WHITE_ROOK;
        }
        return BLACK_ROOK;
    }
}
