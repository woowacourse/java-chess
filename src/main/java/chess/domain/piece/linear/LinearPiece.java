package chess.domain.piece.linear;

import chess.domain.board.Board;
import chess.domain.movepattern.MovePattern;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public final class LinearPiece implements Piece {

    private final Type type;
    private final Side side;
    private final List<MovePattern> movePatterns;

    public LinearPiece(final Type type, final Side side, final List<MovePattern> movePatterns) {
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
        Position currentPosition = source;
        boolean canMoveMore = true;
        while (canMoveMore) {
            Position nextPosition = currentPosition.move(movePattern);
            canMoveMore = canMoveMore(nextPosition, board);
            movablePosition.add(nextPosition);
            currentPosition = nextPosition;
        }
        if (isLastPositionInvalid(source, currentPosition, board)) {
            movablePosition.remove(currentPosition);
        }
        return movablePosition;
    }

    private boolean canMoveMore(@Nullable final Position position, final Board board) {
        return (position != null) && (board.findSideByPosition(position).isNeutrality());
    }

    private boolean isLastPositionInvalid(final Position source, @Nullable final Position lastPosition, final Board board) {
        return lastPosition == null || board.isAllyPosition(source, lastPosition);
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
