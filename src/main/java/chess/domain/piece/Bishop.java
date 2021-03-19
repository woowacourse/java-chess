package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public class Bishop extends Piece {

    public Bishop(TeamColor teamColor, Position position) {
        super("b", teamColor, Score.from(3), Direction.diagonalDirections(), Direction.diagonalDirections(), true, position);
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
