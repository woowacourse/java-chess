package chess.domain.piece;

public class Blank extends Piece {

    private static final String SIGNATURE = ".";

    public Blank(Position position) {
        super(position, SIGNATURE);
    }

    public boolean move(Piece piece) {
        throw new IllegalStateException("빈 칸은 이동할 수 없습니다.");
    }

    @Override
    public boolean isBlank() {
        return true;
    }
}
