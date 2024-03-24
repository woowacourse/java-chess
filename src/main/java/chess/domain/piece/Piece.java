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

    public abstract boolean isCatchable(Position from, Position to);

    public boolean isSameColor(Piece piece) {
        return color == piece.color;
    }

    public boolean isSameColor(Color otherColor) {
        return color == otherColor;
    }

    public boolean isMovable(Position from, Position to) {
        FileDifference fileDifference = from.calculateFileDifferenceTo(to);
        RankDifference rankDifference = from.calculateRankDifferenceTo(to);
        return moveRule.obey(fileDifference, rankDifference);
    }
}
