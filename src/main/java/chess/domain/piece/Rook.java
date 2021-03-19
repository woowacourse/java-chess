package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public class Rook extends Piece {

    public Rook(TeamColor teamColor, Position position) {
        super("r", teamColor, Score.from(5), Direction.straightDirections(), Direction.straightDirections(), true, position);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
