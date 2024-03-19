package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;

public class King extends Piece {
    public King(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Kind findKind() {
        return Kind.KING;
    }
}
