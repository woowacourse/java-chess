package domain.piece;

import domain.board.Board;
import domain.board.Position;

import java.util.List;
import java.util.stream.Stream;

import static domain.piece.CommonMovementDirection.UP;
import static domain.piece.CommonMovementDirection.DOWN;
import static domain.piece.CommonMovementDirection.RIGHT;
import static domain.piece.CommonMovementDirection.LEFT;
import static domain.piece.CommonMovementDirection.calculateDirection;
import static domain.piece.PieceType.ROOK;


public class Rook extends Piece {
    private static final List<CommonMovementDirection> MOVABLE_DIRECTIONS = List.of(UP, DOWN, RIGHT, LEFT);
    private static final PieceType PIECE_TYPE = ROOK;

    public Rook(final PieceColor color) {
        super(color);
    }

    @Override
    public void move(final Position source, final Position destination, final Board board) {
        CommonMovementDirection movementDirection = calculateDirection(source, destination);
        validateMovementDirection(movementDirection);

        List<Position> movePaths = Stream.iterate(source, current -> current.next(movementDirection))
                .takeWhile(current -> current.equals(source) || isContinuable(current, destination, board))
                .toList();

        Position alivePosition = movePaths.get(movePaths.size() - 1).next(movementDirection);
        checkAlivePosition(alivePosition, board);
    }

    private void validateMovementDirection(final CommonMovementDirection movementDirection) {
        if (!MOVABLE_DIRECTIONS.contains(movementDirection)) {
            throw new IllegalArgumentException("방향이 유효하지 않아 이동할 수 없는 칸입니다.");
        }
    }

    private boolean isContinuable(final Position current, final Position destination, final Board board) {
        if (current.equals(destination)) {
            return false;
        }

        if (board.existPiece(current)) {
            throw new IllegalArgumentException("목적지 경로에 기물이 존재하여 이동할 수 없습니다.");
        }

        return true;
    }

    private void checkAlivePosition(final Position alivePosition, final Board board) {
        if (board.existPiece(alivePosition) && board.existTeamColor(alivePosition, color)) {
            throw new IllegalArgumentException("아군 기물이 위치한 칸으로는 이동할 수 없습니다.");
        }
    }

    @Override
    public PieceType pieceType() {
        return PIECE_TYPE;
    }
}
