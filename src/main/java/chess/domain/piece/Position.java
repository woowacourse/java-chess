package chess.domain.piece;

public class Position {
    private final char horizontal;
    private final char vertical;

    public Position(char horizontal, char vertical) {
        validate(horizontal, vertical);
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    private void validate(char horizontal, char vertical) {
        if (horizontal < 'a' || horizontal > 'h' || vertical < '1' || vertical > '8') {
            throw new IllegalArgumentException("체스 말의 위치가 Grid 범위를 벗어났습니다.");
        }
    }
}
