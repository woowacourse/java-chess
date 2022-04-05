package refactorChess.domain.piece;

import refactorChess.domain.board.Direction;
import refactorChess.domain.board.Position;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(PieceColor pieceColor, Position position) {
        super(PieceType.PAWN, pieceColor, position);
    }

    @Override
    public List<Direction> findByMovableDirection(Piece piece, Direction direction) {
        if (piece.isBlank() && isTargetExistToPieceOfMovable(direction)) {
            throw new IllegalArgumentException("유효하지 않은 방향입니다.");
        }
        if (!piece.isBlank() && isTargetNotExistToPieceOfMovable(direction)) {
            throw new IllegalArgumentException("유효하지 않은 방향입니다.");
        }

        return findByMovablePawnDirection();
    }

    private boolean isTargetExistToPieceOfMovable(Direction direction) {
        return direction != Direction.NORTH && direction != Direction.SOUTH;
    }

    private boolean isTargetNotExistToPieceOfMovable(Direction direction) {
        return direction == Direction.NORTH || direction == Direction.SOUTH;
    }

    private List<Direction> findByMovablePawnDirection() {
        if (isBlack()) {
            return Direction.BLACK_PAWN_DIRECTION;
        }
        return Direction.WHITE_PAWN_DIRECTION;
    }

    private boolean isBlack() {
        return this.getPieceColor() == PieceColor.BLACK;
    }

    @Override
    public Direction findByDirection(Position from, Position to) {
        final int column = to.getColumn() - from.getColumn();
        final int row = to.getRow() - from.getRow();

        if (isPawnPositionOfDefault()) {
            return Direction.ofPawnOfDefault(column, row);
        }
        return Direction.of(column, row);
    }

    private boolean isPawnPositionOfDefault() {
        if (isBlack() && getPosition().isSameRow(6)) {
            return true;
        }
        if (!isBlack() && getPosition().isSameRow(1)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
