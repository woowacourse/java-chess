package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public class Knight extends Piece {

    public Knight(TeamColor teamColor, Position position) {
        super("n", teamColor, Score.from(2.5), Direction.knightDirections(), Direction.knightDirections(), false, position);
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
