package chess.domain.position;

import chess.domain.state.State;

public class Target {
    private final Position position;

    private Target(final Position position) {
        this.position = position;
    }

    public static Target valueOf(final Source source, final Position target, final State state) {
        validateTarget(source, target, state);
        return new Target(target);
    }

    private static void validateTarget(final Source source, final Position target, final State state) {
        if (state.findPiece(target).isPresent()) {
            throw new IllegalArgumentException(String.format("같은 색깔의 기물 위치로는 이동할 수 없습니다. 입력 위치: %s", target));
        }
        if (source.isSamePosition(target)) {
            throw new IllegalArgumentException(String.format("source위치와 같은 위치로는 이동할 수 없습니다. 입력 위치: %s", target));
        }
    }

    public Position getPosition() {
        return position;
    }
}
