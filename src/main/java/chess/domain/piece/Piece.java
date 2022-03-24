package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;

public abstract class Piece {

    private final Color color;
    private final String name;

    protected Piece(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public Piece move(Position source, Position target, ChessBoard chessBoard) {
        if (!isMovable(source, target, chessBoard)) {
            throw new IllegalStateException("움직일 수 없는 곳입니다.");
        }
        return this;
    }

    public final String convertedName() {
        return color.convertToCase(name);
    }

    public final boolean isSameColor(Color color) {
        return this.color == color;
    }

    public final boolean isSameTeamPiece(Piece piece) {
        return isSameColor(piece.color);
    }

    abstract public boolean isMovable(Position source, Position target, ChessBoard chessBoard);

    abstract public double score();

    abstract public boolean isPawn();

    abstract public boolean isKing();
}
