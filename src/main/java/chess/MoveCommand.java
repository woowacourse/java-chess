package chess;

public class MoveCommand {

    private final Position from;
    private final Position to;

    private MoveCommand(final Position from, final Position to) {
        validateFromEqualsTo(from, to);
        this.from = from;
        this.to = to;
    }

    public static MoveCommand of(final String value) {
        final String[] splitValue = value.split(" ");
        final Position from = Position.of(splitValue[0]);
        final Position to = Position.of(splitValue[1]);

        return new MoveCommand(from, to);
    }

    private void validateFromEqualsTo(final Position from, final Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("현재 위치와 이동할 위치가 같을 수 없습니다.");
        }
    }

    public Position getFrom() {
        return from;
    }
}
