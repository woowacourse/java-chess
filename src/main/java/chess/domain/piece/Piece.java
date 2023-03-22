package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.movepattern.MovePattern;
import chess.domain.position.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final Type type;
    protected final Side side;

    public Piece(final Type type, final Side side) {
        validate(type, side);
        this.type = type;
        this.side = side;
    }

    protected boolean isRangeValid(final Position position, final MovePattern movePattern) {
        final int nextRank = position.getRankIndex() + movePattern.rankVector();
        final int nextFile = position.getFileIndex() + movePattern.fileVector();
        return nextRank >= 1 && nextRank <= 8 && nextFile >= 1 && nextFile <= 8;
    }

    public String getName() {
        return type.getSymbol(side);
    }

    public Side getSide() {
        return side;
    }

    protected abstract void validate(final Type type, final Side side);

    protected abstract List<MovePattern> getMovePatterns();

    public abstract List<Position> findMovablePositions(final Position source, final Board board);

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
}
