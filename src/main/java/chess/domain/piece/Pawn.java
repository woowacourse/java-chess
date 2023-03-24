package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.movepattern.PawnMovePattern;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.Nullable;

public class Pawn implements Piece {

    private final Type type;
    private final Side side;
    private final List<PawnMovePattern> movePatterns;
    private boolean notMoved;

    public Pawn(final Side side) {
        this.type = Type.PAWN;
        this.side = side;
        this.movePatterns = initMovePatterns();
        this.notMoved = true;
    }

    private List<PawnMovePattern> initMovePatterns() {
        List<PawnMovePattern> movePatterns = new ArrayList<>();
        if (side.isWhite()) {
            movePatterns.add(PawnMovePattern.UP);
            movePatterns.add(PawnMovePattern.LEFT_TOP);
            movePatterns.add(PawnMovePattern.RIGHT_TOP);
        }
        if (side.isBlack()) {
            movePatterns.add(PawnMovePattern.DOWN);
            movePatterns.add(PawnMovePattern.LEFT_BOTTOM);
            movePatterns.add(PawnMovePattern.RIGHT_BOTTOM);
        }
        return movePatterns;
    }

    @Override
    public List<Position> findMovablePosition(final Position source, final Board board) {
        final List<Position> movablePositions = new ArrayList<>();

        for (final PawnMovePattern movePattern : movePatterns) {
            movablePositions.addAll(
                    findMovablePositionByMovePattern(source, movePattern, board)
            );
        }

        return movablePositions;
    }

    private List<Position> findMovablePositionByMovePattern(
            final Position source,
            final PawnMovePattern movePattern,
            final Board board
    ) {
        if (movePattern.isDiagonalMove()) {
            return findMovablePositionByDiagonalMovePattern(source, movePattern, board);
        }
        return findMovablePositionByNonDiagonalMovePattern(source, movePattern, board);
    }

    private List<Position> findMovablePositionByDiagonalMovePattern(
            final Position source,
            final PawnMovePattern movePattern,
            final Board board
    ) {
        List<Position> movablePosition = new ArrayList<>();
        final Position nextPosition = source.move(movePattern);
        if (isDiagonalNextPositionValid(source, nextPosition, board)) {
            movablePosition.add(nextPosition);
        }
        return movablePosition;
    }

    private boolean isDiagonalNextPositionValid(final Position source, @Nullable final Position nextPosition, final Board board) {
        return isPositionNotNull(nextPosition) && board.isEnemyPosition(source, nextPosition);
    }

    private boolean isPositionNotNull(@Nullable final Position position) {
        return position != null;
    }

    private List<Position> findMovablePositionByNonDiagonalMovePattern(
            final Position source,
            final PawnMovePattern movePattern,
            final Board board
    ) {
        List<Position> movablePosition = new ArrayList<>();
        final Position nextPosition = source.move(movePattern);
        boolean isNextPositionInvalid = true;
        if (isNonDiagonalNextPositionValid(nextPosition, board)) {
            movablePosition.add(nextPosition);
            isNextPositionInvalid = false;
        }
        if (isNextPositionInvalid) {
            return movablePosition;
        }
        final Position specialPosition = Objects.requireNonNull(nextPosition).move(movePattern);
        if (notMoved && isNonDiagonalNextPositionValid(specialPosition, board)) {
            movablePosition.add(specialPosition);
        }
        return movablePosition;
    }

    private boolean isNonDiagonalNextPositionValid(@Nullable final Position nextPosition, final Board board) {
        return isPositionNotNull(nextPosition) && board.findSideByPosition(nextPosition).isNeutrality();
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
        return true;
    }

    @Override
    public void changePawnMoveState() {
        notMoved = false;
    }
}
