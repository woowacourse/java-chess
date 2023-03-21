package domain.coordinate;

public class MovePosition {

    private final Position source;
    private final Position target;

    private MovePosition(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

    public static MovePosition of(Position source, Position target) {
        return new MovePosition(source, target);
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }

    public boolean isStraight() {
        return target.isStraight(source);
    }

    public boolean isDiagonally() {
        return target.isDiagonally(source);
    }

    public int diffY() {
        return target.diffY(source);
    }

    public int diffX() {
        return target.diffX(source);
    }

}
