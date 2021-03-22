package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import chess.domain.piece.direction.RookDirections;
import chess.domain.piece.moving.RookMoving;

public class Rook extends Piece {

    public Rook(TeamColor teamColor, Position position) {
        super("r", teamColor, Score.from(5), new RookMoving(new RookDirections(), position));
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
