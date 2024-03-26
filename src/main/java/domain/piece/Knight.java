package domain.piece;

import domain.board.Board;
import domain.board.Position;

import static domain.piece.PieceType.KNIGHT;
import static domain.piece.KnightMovementDirection.calculateDirection;

public class Knight extends Piece {
    private static final PieceType PIECE_TYPE = KNIGHT;

    public Knight(final PieceColor color) {
        super(color);
    }

    @Override
    public void move(final Position source, final Position destination, final Board board) {
        KnightMovementDirection movementDirection = calculateDirection(source, destination);

        Position alivePosition = source.next(movementDirection);
        checkAlivePosition(alivePosition, board);
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
