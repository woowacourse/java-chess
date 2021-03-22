package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.condition.MoveCondition;

import java.util.List;

public abstract class Piece implements ChessPiece {

    private Position position;
    private final List<MoveCondition> moveConditions;

    public Piece(final Position position, final List<MoveCondition> moveConditions) {
        this.position = position;
        this.moveConditions = moveConditions;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public int getRow() {
        return position.getRow();
    }

    @Override
    public int getColumn() {
        return position.getColumn();
    }

    @Override
    public boolean isSamePosition(final Position position) {
        return this.position.equals(position);
    }

    @Override
    public void move(final Position target, final Board board) {
        this.position = position.changePosition(target, board, this, moveConditions);
    }

    @Override
    public Position getPosition() {
        return new Position(position.getRow(), position.getColumn());
    }

    @Override
    public boolean equals(Object o) {
        return isSamePiece(o);
    }

    @Override
    public int hashCode() {
        return toHashCode();
    }

    abstract protected boolean isSamePiece(Object o);

    abstract protected int toHashCode();

}
