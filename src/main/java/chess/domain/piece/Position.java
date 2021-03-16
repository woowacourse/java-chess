package chess.domain.piece;

public class Position {
    private static final int VALID_LENGTH = 2;
    private static final int HORIZONTAL_INDEX = 0;
    private static final int VERTICAL_INDEX = 1;
    private static final char MIN_HORIZONTAL_RANGE = 'a';
    private static final char MAX_HORIZONTAL_RANGE = 'h';
    private static final char MIN_VERTICAL_RANGE = '1';
    private static final char MAX_VERTICAL_RANGE = '8';



    private final char horizontal;
    private final char vertical;

    public Position(char horizontal, char vertical) {
        validate(horizontal, vertical);
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    private void validate(char horizontal, char vertical) {
        if (horizontal < MIN_HORIZONTAL_RANGE || horizontal > MAX_HORIZONTAL_RANGE
                || vertical < MIN_VERTICAL_RANGE || vertical > MAX_VERTICAL_RANGE) {
            throw new IllegalArgumentException("체스 말의 위치가 Grid 범위를 벗어났습니다.");
        }
    }

    public Position(String position) {
        this(position.charAt(HORIZONTAL_INDEX), position.charAt(VERTICAL_INDEX));
    }

    private void validateLength(String position) {
        if (position.length() != VALID_LENGTH) {
            throw new IllegalArgumentException("잘못된 입력 값입니다.");
        }
    }


}
