package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public final class Bishop extends Piece {

    public Bishop(TeamColor teamColor, Position position) {
        super(new Details("b", teamColor, Score.from(3), true),
            new Directions(Direction.diagonalDirections(), Direction.diagonalDirections()),
            position);
    }
}
