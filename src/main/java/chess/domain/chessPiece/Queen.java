package chess.domain.chessPiece;

import chess.domain.position.Position;

import static chess.domain.chessPiece.Role.*;

public class Queen extends Piece {

    public Queen(Team team) {
        super(team);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int rowDistance = source.calculateRowDistance(target);
        int columnDistance = source.calculateColumnDistance(target);
        if (source.isDifferentRow(target) && source.isDifferentColumn(target) && rowDistance != columnDistance) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public Role getRole() {
        if (team.isWhite()) {
            return WHITE_QUEEN;
        }
        return BLACK_QUEEN;
    }
}
