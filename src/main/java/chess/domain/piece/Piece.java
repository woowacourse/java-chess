package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;
import chess.domain.piece.strategy.MoveStrategy;

public abstract class Piece {
    protected final String name;
    protected final Color color;
    protected final Score score;
    protected final MoveStrategy moveStrategy;

    public Piece(String name, Color color, Score score, MoveStrategy moveStrategy) {
        this.name = name;
        this.color = color;
        this.score = score;
        this.moveStrategy = moveStrategy;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Score getScore() {
        return score;
    }

    public boolean isSameColor(Piece piece) {
        return color == piece.color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public abstract boolean canMove(Position source, Position target);

    public boolean isKnight() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }
}
