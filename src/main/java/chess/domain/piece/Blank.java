package chess.domain.piece;

public class Blank extends Piece {

    private static final String SIGNATURE = ".";

    public Blank(Position position) {
        super(position, SIGNATURE);
    }

    public void move(Piece piece) {
        throw new IllegalStateException("빈 칸은 이동할 수 없습니다.");
    }
}
