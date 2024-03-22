package chess.domain.chessPiece;

import chess.domain.position.Position;

import static chess.domain.chessPiece.Role.*;

public class Rook extends Piece {

    public Rook(Team team) {
        super(team);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        if (source.isDifferentColumn(target) && source.isDifferentRow(target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public Role getRole() {
        if (team.isWhite()) {
            return WHITE_ROOK;
        }
        return BLACK_ROOK;
    }
}
