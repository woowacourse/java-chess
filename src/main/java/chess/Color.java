package chess;

public enum Color {

    BLACK(Direction.UP), WHITE(Direction.DOWN);

    private final Direction backward;

    Color(Direction backward) {
        this.backward = backward;
    }

    public boolean isBackward(Direction direction) {
        return backward == direction;
    }
}
