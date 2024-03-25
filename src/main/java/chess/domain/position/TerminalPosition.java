package chess.domain.position;

public class TerminalPosition {
    private final Position start;
    private final Position end;

    public TerminalPosition(Position start, Position end) {
        validate(start, end);
        this.start = start;
        this.end = end;
    }

    private void validate(Position start, Position end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("시작점과 끝점은 같을 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Path{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }
}
