package chess.domain.piece;

import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public abstract class GamePiece implements Piece {

    private final Side side;
    private final String initial;
    private boolean initPosition = true;

    public GamePiece(Side side, String initial) {
        this.side = side;
        this.initial = initial;
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

    protected abstract boolean movable(int rowDifference, int columnDifference);

    protected abstract List<Position> getRoute(Position from, Position to);

    // TODO moved, isInitPosition 폰만 사용. initPosition자체를 폰에게?
    //  두 메서드를 폰제외하고 모두 구현 vs 게임피스에서 구현하고 폰에서 오버라이드
    //  둘 다 최악
    //  폰 제외한 게임피스를 추상 클래스로 추상화: moved, isInitPosition, diagonal, forward 4개 메서드 중복제거 가능
    @Override
    public final void moved() {
        initPosition = false;
    }

    @Override
    public final boolean isSideEqualTo(Side side) {
        return this.side == side;
    }

    @Override
    public final boolean isBlank() {
        return false;
    }

    @Override
    public final String getInitial() {
        if (isSideEqualTo(Side.WHITE)) {
            return initial.toLowerCase(Locale.ROOT);
        }
        if (isSideEqualTo(Side.BLACK)) {
            return initial.toUpperCase(Locale.ROOT);
        }
        return initial;
    }

    protected final boolean isInitPosition() {
        return initPosition;
    }
}
