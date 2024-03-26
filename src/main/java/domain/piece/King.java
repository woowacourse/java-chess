package domain.piece;

import domain.board.Board;
import domain.board.Position;

import static domain.piece.PieceType.KING;
import static domain.piece.CommonMovementDirection.calculateDirection;

public class King extends Piece {
    private static final PieceType PIECE_TYPE = KING;

    public King(final PieceColor color) {
        super(color);
    }

    @Override
    public void move(final Position source, final Position destination, final Board board) {
        CommonMovementDirection movementDirection = calculateDirection(source, destination);
        checkMoveDistance(source, destination, movementDirection);

        Position alivePosition = source.next(movementDirection);

        checkAlivePosition(alivePosition, board);
    }

    private void checkMoveDistance(final Position source, final Position destination, final CommonMovementDirection movementDirection) {
        int rowDistance = destination.rowIndex() - source.rowIndex();
        int columnDistance = destination.columnIndex() - source.columnIndex();

        if (movementDirection.getRowDistance() != rowDistance || movementDirection.getColumnDistance() != columnDistance) {
            throw new IllegalArgumentException("이동할 수 없는 거리입니다.");
        }
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
