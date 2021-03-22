package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import chess.domain.piece.direction.BishopDirections;
import chess.domain.piece.moving.BishopMoving;

public class Bishop extends Piece {

    public Bishop(TeamColor teamColor, Position position) {
        super("b", teamColor, Score.from(3), new BishopMoving(new BishopDirections(), position));
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
