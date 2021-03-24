package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import java.util.List;
import java.util.Objects;

public abstract class GamePiece extends Piece {

    public GamePiece(Side side, String initial) {
        super(side, initial);
    }

    @Override
    public final List<Position> route(Position from, Position to) {
        if (Objects.equals(from, to)) {
            throw new InvalidMovementException("자기 자신의 위치로는 이동할 수 없습니다.");
        }

        int rowDifference = Position.differenceOfRow(from, to);
        int columnDifference = Position.differenceOfColumn(from, to);

        if (movable(rowDifference, columnDifference)) {
            return getRoute(from, to);
        }

        throw new InvalidMovementException("해당 기물의 이동 룰에 어긋납니다.");
    }

    @Override
    public final boolean isBlank() {
        return false;
    }
}
