package chess.domain;

public class Empty extends Piece {

    private static final String EMPTY = ".";

    public Empty(final String name, final Side side) {
        super(name, side);
    }

    @Override
    protected void validate(final String name, final Side side) {
        if (!name.equals(EMPTY)) {
            throw new IllegalArgumentException("Empty의 이름은 점이여야 합니다.");
        }
        if (!side.isNeutrality()) {
            throw new IllegalArgumentException("Empty의 진영은 중립이여야 합니다.");
        }
    }
}
