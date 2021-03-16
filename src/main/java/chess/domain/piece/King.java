package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private static final Position INITIAL_BLACK_POSITION = Position.of(0, 4);
    private static final Position INITIAL_WHITE_POSITION = Position.of(7, 4);

    public King(Position position, String name) {
        super(position, name);
    }

    public static List<King> generate() {
        List<King> kings = new ArrayList();
        kings.add(new King(INITIAL_BLACK_POSITION, "K"));
        kings.add(new King(INITIAL_WHITE_POSITION, "k"));
        return kings;
    }
}
