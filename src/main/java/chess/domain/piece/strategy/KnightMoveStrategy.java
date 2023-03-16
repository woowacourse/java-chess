package chess.domain.piece.strategy;

import chess.domain.square.Square;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public void move(final Square current, final Square destination) {
        for (KnightDirection value : KnightDirection.values()) {
            try {
                Square next = current.move(value.getFileDifference(), value.getRankDifference());
                if (destination.equals(next)) {
                    return;
                }
            } catch (IllegalArgumentException ignored) {}
        }
        throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
    }
}
