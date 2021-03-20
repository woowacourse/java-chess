package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;

public final class Pawn extends Piece {

    public Pawn(TeamColor teamColor, Position position) {
        super(new Details("p", teamColor, Score.from(1), false),
            new Directions(Direction.forwardDirection(teamColor), Direction.forwardDiagonal(teamColor)),
            position);
    }

}
