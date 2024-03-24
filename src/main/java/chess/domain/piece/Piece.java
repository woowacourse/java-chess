package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

public abstract class Piece {

    private final Color color;
    private final Rule moveRule;

    public Piece(Color color, Rule moveRule) {
        this.color = color;
        this.moveRule = moveRule;
    }

    public boolean isSameColor(Piece piece) {
        return color == piece.color;
    }

    public boolean isSameColor(Color otherColor) {
        return color == otherColor;
    }

    public boolean isMovable(FileDifference fileDifference, RankDifference rankDifference) {
        return moveRule.obey(fileDifference, rankDifference);
    }

    public abstract boolean isCatchable(FileDifference fileDifference, RankDifference rankDifference);
}
