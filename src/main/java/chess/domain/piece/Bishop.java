package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Score;
import chess.domain.TeamColor;
import javax.swing.text.Position;

public final class Bishop extends Piece {

    public Bishop(TeamColor teamColor, Position position) {
        super(new Details("b", teamColor, Score.from(3), true),
            new Directions(Direction.diagonalDirections(), Direction.diagonalDirections()));
    }
}
