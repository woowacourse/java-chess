package chess.domain.piece.immediate;

import chess.domain.board.Board;
import chess.domain.movepattern.MovePattern;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public final class ImmediatePiece implements Piece {

    private final Type type;
    private final Side side;
    private final List<MovePattern> movePatterns;

    public ImmediatePiece(final Type type, final Side side, final List<MovePattern> movePatterns) {
        this.type = type;
        this.side = side;
        this.movePatterns = movePatterns;
    }

    @Override
    public List<Position> findMovablePosition(final Position source, final Board board) {
        final List<Position> movablePositions = new ArrayList<>();

        for (final MovePattern movePattern : movePatterns) {
            movablePositions.addAll(
                    findMovablePositionByMovePattern(source, movePattern, board)
            );
        }

        return movablePositions;
    }

    private List<Position> findMovablePositionByMovePattern(
            final Position source,
            final MovePattern movePattern,
            final Board board) {

        List<Position> movablePosition = new ArrayList<>();
        final Position nextPosition = source.move(movePattern);
        if (isNextPositionValid(source, nextPosition, board)) {
            movablePosition.add(nextPosition);
        }
        return movablePosition;
    }

    private boolean isNextPositionValid(final Position source, @Nullable final Position nextPosition, final Board board) {
        return isPositionNotNull(nextPosition) && !board.isAllyPosition(source, nextPosition);
    }

    private boolean isPositionNotNull(@Nullable final Position position) {
        return position != null;
    }

    @Override
    public String name() {
        return type.getSymbol(side);
    }

    @Override
    public Type type() {
        return type;
    }

    @Override
    public Side side() {
        return side;
    }

    @Override
    public double price() {
        return type.price();
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public void changePawnMoveState() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }
}
