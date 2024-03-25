package chess.domain.chesspiece;

import chess.domain.position.Position;

import static chess.domain.chesspiece.Role.*;

public class King extends Piece {

    public King(Team team) {
        super(team);
    }

    //TODO : 리팩토링
    @Override
    protected void validateMovingRule(Position source, Position target) {
        int rowDistance = source.calculateRowDistance(target);
        int columnDistance = source.calculateColumnDistance(target);
        if (rowDistance != 1 && columnDistance != 1) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public Role getRole() {
        if (team.isWhite()) {
            return WHITE_KING;
        }
        return BLACK_KING;
    }
}
