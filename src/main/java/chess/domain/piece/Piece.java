package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.strategy.MoveStrategy;
import java.util.List;

public abstract class Piece {

    private final Color color;
    private final MoveStrategy moveStategy;
    protected Type type;

    public Piece(Color color, MoveStrategy moveStrategy) {
        this.color = color;
        this.moveStategy = moveStrategy;
    }

    public String getName() {
        return type.nameByColor(color);
    }

    public Color getColor() {
        return color;
    }

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

    public double score() {
        return type.getScore();
    }

    public abstract List<Direction> direction();

    public boolean isMovable(
        ChessBoard chessBoard,
        Position sourcePosition,
        Position targetPosition
    ) {
        return moveStategy.movable(chessBoard, sourcePosition, targetPosition, this);
    }
}
