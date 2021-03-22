package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import chess.domain.piece.direction.KnightDirections;
import chess.domain.piece.moving.KnightMoving;

public class Knight extends Piece {

    public Knight(TeamColor teamColor, Position position) {
        super("n", teamColor, Score.from(2.5), new KnightMoving(new KnightDirections(), position));
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
