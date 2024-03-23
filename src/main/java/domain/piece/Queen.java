package domain.piece;

import domain.board.Position;
import domain.game.PieceType;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static domain.game.PieceType.QUEEN;
import static domain.piece.CommonMovementDirection.*;

public class Queen extends Piece {
    private static final PieceType PIECE_TYPE = QUEEN;

    public Queen(final PieceColor color) {
        super(color);
    }

    @Override
    public void checkMovable(final Position source, final Position destination, final Map<Position, Piece> piecePositions) {
        CommonMovementDirection movementDirection = calculateDirection(source, destination);

        Position alivePosition = move(source, destination, movementDirection, piecePositions);

        checkAlivePosition(alivePosition, piecePositions);
    }

    private Position move(
            final Position source,
            final Position destination,
            final CommonMovementDirection movementDirection,
            final Map<Position, Piece> piecePositions
    ) {
        List<Position> movePaths = Stream.iterate(source, current -> current.next(movementDirection))
                .takeWhile(current -> isContinuable(current, destination, piecePositions))
                .toList();

        return movePaths.get(movePaths.size() - 1)
                .next(movementDirection);
    }

    private boolean isContinuable(final Position current, final Position destination, final Map<Position, Piece> piecePositions) {
        if (current.equals(destination)) {
            return false;
        }

        if (piecePositions.containsKey(current)) {
            throw new IllegalArgumentException("목적지 경로에 기물이 존재하여 이동할 수 없습니다.");
        }

        return true;
    }

    private void checkAlivePosition(final Position alivePosition, final Map<Position, Piece> piecePositions) {
        if (piecePositions.containsKey(alivePosition) && !checkEnemy(piecePositions.get(alivePosition))) {
            throw new IllegalArgumentException("아군 기물이 위치한 칸으로는 이동할 수 없습니다.");
        }
    }

    @Override
    public PieceType pieceType() {
        return PIECE_TYPE;
    }
}
