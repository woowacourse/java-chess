package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public class King extends Piece {

    public King(TeamColor teamColor, Position position) {
        super("k", teamColor, Score.from(0), Direction.aroundDirections(), Direction.aroundDirections(), false, position);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
