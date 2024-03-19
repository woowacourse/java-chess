package chess.piece;

import chess.Rule;
import chess.position.Position;
import chess.position.PositionDifference;
import chess.position.RankDifference;

public class Pawn extends Piece {

    private final Rule rule = (fileDifference, rankDifference) -> rankDifference.equals(new RankDifference(1));

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        PositionDifference positionDifference= from.calculateDifferenceTo(to);
        return positionDifference.isObeyRule(rule);
    }
}
