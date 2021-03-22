package chess.domain.piece;

public class Blank extends Piece {
    private static final String BLANK_NOTATION = ".";
    private static final Blank BLANK = new Blank();

    private Blank() {
        super(BLANK_NOTATION, moveOrder -> {
            throw new IllegalArgumentException("해당 칸에는 기물이 없습니다.");
        });
    }

    public static Blank getInstance(){
        return BLANK;
    }
}
