package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Position;
import java.util.List;

public abstract class Piece {

    protected final Color color;
    protected Type type;
    protected Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public boolean isSameColor(Piece piece) {
        return this.getColor().equals(piece.getColor());
    }

    public void move(Position position) {
        this.position = position;
    }

    public String getName() {
        return type.nameByColor(color);
    }

    public Color getColor() {
        return color;
    }

    public boolean isBlank() {
        return type == Type.BLANK;
    }

    public abstract List<Position> getMovablePositions(ChessBoard chessBoard);

    public boolean isMovable(ChessBoard chessBoard, Position nextPosition) {
        return isInBound(nextPosition) && chessBoard.isBlank(nextPosition);
    }

    public boolean isAttackMove(ChessBoard chessBoard, Position nextPosition) {
        return isInBound(nextPosition) && chessBoard.isAttackMove(this, nextPosition);
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public boolean isInBound(Position currentPosition) {
        return currentPosition.getRow() < 8
            && currentPosition.getRow() >= 0
            && currentPosition.getColumn() < 8
            && currentPosition.getColumn() >= 0;
    }
}
