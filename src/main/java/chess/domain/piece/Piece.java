package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.Position;
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

    public boolean isMovable(Position from, Position to) {
        FileDifference fileDifference = from.calculateFileDifference(to);
        RankDifference rankDifference = from.calculateRankDifference(to);
        return moveRule.obey(fileDifference, rankDifference);
    }

    public abstract boolean isCatchable(Position from, Position to);
}
