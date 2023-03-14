package chess.domain;

public class King extends Piece {

    private static final String UPPER_NAME = "K";
    private static final String LOWER_NAME = "k";

    public King(final String name, final Side side) {
        super(name, side);
    }

    @Override
    protected void validate(final String name, final Side side) {
        if (name.equals(UPPER_NAME) && side.isWhite()) {
            throw new IllegalArgumentException("흰색 진영일때는 대문자 이름이 올 수 없습니다.");
        }
        if (name.equals(LOWER_NAME) && side.isBlack()) {
            throw new IllegalArgumentException("검정색 진영일때는 소문자 이름이 올 수 없습니다.");
        }
    }
}
