package chess.domain.chesspiece;

import chess.domain.position.Position;

import static chess.domain.chesspiece.Role.BLACK_BISHOP;
import static chess.domain.chesspiece.Role.WHITE_BISHOP;

public class Bishop extends Piece {

    public Bishop(Team team) {
        super(team);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int rowDistance = source.calculateRowDistance(target);
        int columnDistance = source.calculateColumnDistance(target);
        if (rowDistance != columnDistance) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public Role getRole() {
        if (team.isWhite()) {
            return WHITE_BISHOP;
        }
        return BLACK_BISHOP;
    }
}
