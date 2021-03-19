package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Score;
import chess.domain.TeamColor;
import javax.swing.text.Position;

public final class Queen extends Piece {

    public Queen(TeamColor teamColor, Position position) {
        super(new Details("q", teamColor, Score.from(9), true),
            new Directions(Direction.aroundDirections(), Direction.aroundDirections()));
    }
}
