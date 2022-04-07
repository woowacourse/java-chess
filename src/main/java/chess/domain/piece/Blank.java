package chess.domain.piece;

public class Blank extends Piece {

    private static final String SIGNATURE = "·";
    private static final double SCORE = 0.0;

    public Blank(Position position) {
        super(position, SIGNATURE);
    }

    public boolean isMovable(Piece piece) {
        throw new IllegalStateException("빈 칸은 이동할 수 없습니다.");
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String getName() {
        return "blank";
    }
}
