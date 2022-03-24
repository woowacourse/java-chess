package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;

public abstract class AbstractPiece implements Piece {

    private final Color color;
    private final String name;

    protected AbstractPiece(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    @Override
    public Piece move(Position source, Position target, ChessBoard chessBoard) {
        if (!isMovable(source, target, chessBoard)) {
            throw new IllegalStateException("움직일 수 없는 곳입니다.");
        }
        return this;
    }

    @Override
    public final String convertedName() {
        return color.convertToCase(name);
    }

    @Override
    public final boolean isSameColor(Color color) {
        return this.color == color;
    }

    @Override
    public final boolean isSameTeamPiece(Piece piece) {
        return isSameColor(piece.color());
    }

    @Override
    public final Color color() {
        return color;
    }
}
