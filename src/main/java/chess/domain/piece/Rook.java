package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Score;
import chess.domain.TeamColor;
import javax.swing.text.Position;

public final class Rook extends Piece {

    public Rook(TeamColor teamColor, Position position) {
        super(new Details("r", teamColor, Score.from(5), true),
            new Directions(Direction.straightDirections(), Direction.straightDirections()));
    }
}
