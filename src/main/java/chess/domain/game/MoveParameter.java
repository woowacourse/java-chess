package chess.domain.game;

import chess.domain.position.Position;

import java.util.List;

public class MoveParameter {
    private final Position source;
    private final Position target;

    private MoveParameter(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

    public static MoveParameter of(List<String> parameters) {
        validate(parameters);
        Position source = Position.of(parameters.get(0));
        Position target = Position.of(parameters.get(1));
        validatePosition(source, target);
        return new MoveParameter(source, target);
    }

    private static void validatePosition(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("같은 지점으로의 이동은 불가능 합니다.");
        }
    }

    private static void validate(List<String> parameters) {
        if (parameters.size() != 2) {
            throw new IllegalArgumentException("이동하기 위해서는 source와 target 위치를 입력하셔야 합니다.");
        }
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}