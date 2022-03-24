package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.strategy.PieceMovableStrategy;

public abstract class Piece {

    private final Color color;
    private final String name;
    private final PieceMovableStrategy pieceMovableStrategy;

    protected Piece(Color color, String name, PieceMovableStrategy pieceMovableStrategy) {
        this.color = color;
        this.name = name;
        this.pieceMovableStrategy = pieceMovableStrategy;
    }

    public Piece move(Position start, Position target, ChessBoard chessBoard) {
        if (!pieceMovableStrategy.isMovable(start, target, chessBoard)) {
            throw new IllegalStateException("움직일 수 없는 곳입니다.");
        }
        return this;
    }

    public final String convertedName() {
        return color.convertToCase(name);
    }

    public final boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    abstract public double score();
}
