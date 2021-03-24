package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import chess.domain.piece.direction.KingDirections;
import chess.domain.piece.moving.KingMoving;

public class King extends Piece {

    public King(TeamColor teamColor, Position position) {
        super("k", teamColor, Score.from(0), new KingMoving(new KingDirections(), position));
    }
    @Override
    public boolean isKing() {
        return true;
    }
}
