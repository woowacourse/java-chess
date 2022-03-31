package chess.domain.refactorPiece;

import chess.domain.piece.attribute.Color;
import chess.domain.refactorBoard.ChessBoard;
import chess.domain.refactorPosition.Position;

public abstract class Piece implements Article {

    private final Color color;
    private final String name;

    public Piece(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    @Override
    public Piece move(Position from, Position to, ChessBoard chessBoard) {
        if (!isMovable(from, to, chessBoard)) {
            throw new IllegalArgumentException("움직일 수 없는 이동입니다.");
        }

        return this;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", name='" + name + '\'' +
                '}';
    }
}
