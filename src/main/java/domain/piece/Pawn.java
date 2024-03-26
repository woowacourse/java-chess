package domain.piece;

import domain.board.Board;
import domain.board.Position;

import static domain.board.Rank.SEVEN;
import static domain.board.Rank.TWO;
import static domain.piece.PieceType.PAWN;
import static domain.piece.PawnMovementDirection.calculateDirection;
import static domain.piece.PieceColor.BLACK;
import static domain.piece.PieceColor.WHITE;

public class Pawn extends Piece {
    private static final PieceType PIECE_TYPE = PAWN;

    public Pawn(final PieceColor color) {
        super(color);
    }

    @Override
    public void move(final Position source, final Position destination, final Board board) {
        PawnMovementDirection movementDirection = calculateDirection(color, source, destination);
        if (movementDirection.isCrossStep()) {
            checkAlivePositionOfCrossStep(source.next(movementDirection), board);
            return;
        }

        if (movementDirection.isTwoStep()) {
            checkIsStartPosition(source);
        }
        checkMovePaths(source, movementDirection, board);
    }

    private void checkAlivePositionOfCrossStep(final Position alivePosition, final Board board) {
        if (!board.existPiece(alivePosition) || board.existTeamColor(alivePosition, color)) {
            throw new IllegalArgumentException("적 기물이 존재하지 않으면 대각선으로 이동할 수 없습니다");
        }
    }

    private void checkIsStartPosition(final Position source) {
        if ((color == WHITE && source.rank() != TWO) || (color == BLACK && source.rank() != SEVEN)) {
            throw new IllegalArgumentException("시작 위치가 아니면 두 칸 이동할 수 없습니다.");
        }
    }

    private void checkMovePaths(
            final Position source,
            final PawnMovementDirection movementDirection,
            final Board board
    ) {
        Position current = source;
        int moveDistance = Math.abs(movementDirection.getRowDistance());
        for (int i = 0; i < moveDistance; i++) {
            current = current.next(movementDirection.convertOneStep());
            checkPathHasPiece(current, board);
        }
    }

    private void checkPathHasPiece(final Position path, final Board board) {
        if (board.existPiece(path)) {
            throw new IllegalArgumentException("전진시 기물이 존재하는 경로 혹은 목적지로 이동할 수 없습니다.");
        }
    }

    @Override
    public PieceType pieceType() {
        return PIECE_TYPE;
    }
}
