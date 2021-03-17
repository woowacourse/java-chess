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

    public String getColor() {
        return color.toString();
    }

    public boolean isBlank() {
        return type == Type.BLANK;
    }

    public abstract List<Position> getMovablePositions(ChessBoard chessBoard);

    protected boolean isMovable(ChessBoard chessBoard, Position nextPosition) {
        return isInBound(nextPosition) && chessBoard.isBlank(nextPosition);
    }

    protected boolean isAttackMove(ChessBoard chessBoard, Position nextPosition) {
        return isInBound(nextPosition) && chessBoard.isAttackMove(this, nextPosition);
    }

    protected boolean isBlack() {
        return this.color.isBlack();
    }

    protected boolean isWhite() {
        return this.color.isWhite();
    }

    protected boolean isInBound(Position currentPosition) {
        return currentPosition.getRow() < 8
            && currentPosition.getRow() >= 0
            && currentPosition.getCol() < 8
            && currentPosition.getCol() >= 0;
    }
}
