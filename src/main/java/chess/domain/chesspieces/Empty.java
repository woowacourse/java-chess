package chess.domain.chesspieces;

public class Empty extends Square {
    public final static String EMPTY_DISPLAY = ".";

    public Empty() {
        super(EMPTY_DISPLAY);
    }
}
