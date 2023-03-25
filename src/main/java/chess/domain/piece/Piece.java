package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.movepattern.MovePattern;
import chess.domain.position.Position;

import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final Type type;
    protected final Side side;
    protected final List<MovePattern> movePatterns;

    public Piece(final Type type, final Side side, List<MovePattern> movePatterns) {
        validate(type, side);
        this.type = type;
        this.side = side;
        this.movePatterns = movePatterns;
    }

    protected boolean isRangeValid(final Position position, final MovePattern movePattern) {
        final int nextRank = position.getRankIndex() + movePattern.rankVector();
        final int nextFile = position.getFileIndex() + movePattern.fileVector();
        return nextRank >= Board.LOWER_BOUNDARY
                && nextRank <= Board.UPPER_BOUNDARY
                && nextFile >= Board.LOWER_BOUNDARY && nextFile <= Board.UPPER_BOUNDARY;
    }

    public String getName() {
        return type.getSymbol(side);
    }

    public Side getSide() {
        return side;
    }

    public Type getType() {
        return type;
    }

    protected abstract void validate(final Type type, final Side side);

    public abstract List<Position> findMovablePositions(final Position source, final Board board);

    public boolean isWhite() {
        return side.isWhite();
    }

    public boolean isBlack() {
        return side.isBlack();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return type == piece.type && side == piece.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, side);
    }

    public boolean isKing() {
        return type.isKing();
    }

    public boolean isPawn() {
        return type.isPawn();
    }

    public double getScore() {
        return type.getValue();
    }
}
