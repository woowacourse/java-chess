package chess.game;

public class MoveCommand {

    private final Position from;
    private final Position to;

    private MoveCommand(final Position from, final Position to) {
        validateFromEqualsTo(from, to);
        this.from = from;
        this.to = to;
    }

    public static MoveCommand of(final String from, final String to) {
        return new MoveCommand(Position.of(from), Position.of(to));
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    private void validateFromEqualsTo(final Position from, final Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("현재 위치와 이동할 위치가 같을 수 없습니다.");
        }
    }
}
