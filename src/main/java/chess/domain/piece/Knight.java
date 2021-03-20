package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public final class Knight extends Piece {

    public Knight(TeamColor teamColor, Position position) {
        super(new Details("n", teamColor, Score.from(2.5), false),
            new Directions(Direction.knightDirections(), Direction.knightDirections()),
            position);
    }

}
