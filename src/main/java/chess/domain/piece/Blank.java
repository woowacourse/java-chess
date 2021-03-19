package chess.domain.piece;

public class Blank extends Piece {
    private static final String BLANK_NOTATION = ".";

    public Blank() {
        super(BLANK_NOTATION, moveOrder -> {
            throw new IllegalArgumentException("해당 칸에는 기물이 없습니다.");
        });
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Blank) {
            return true;
        }
        return false;
    }
}
