package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Score;
import chess.domain.TeamColor;
import javax.swing.text.Position;

public final class King extends Piece {

    public King(TeamColor teamColor, Position position) {
        super(new Details("k", teamColor, Score.from(0), false),
            new Directions(Direction.aroundDirections(), Direction.aroundDirections()));
    }
}
