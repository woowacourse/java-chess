package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import java.util.List;

public abstract class Piece {

    protected final Color color;
    protected Type type;
    protected Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public String getName() {
        return type.nameByColor(color);
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public void move(Position position) {
        this.position = position;
    }

    public abstract List<Position> getMovablePositions(ChessBoard chessBoard);

    public boolean isSameColor(Piece piece) {
        return this.getColor().equals(piece.getColor());
    }

    public boolean isSameColor(Color color) {
        return this.getColor().equals(color);
    }

    public boolean isBlank() {
        return type == Type.BLANK;
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isKing() {
        return type.equals(Type.KING);
    }

    public boolean isPawn() {
        return type.equals(Type.PAWN);
    }
}
