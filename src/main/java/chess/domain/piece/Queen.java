package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private static final Position INITIAL_BLACK_POSITION = Position.of('d', '8');
    private static final Position INITIAL_WHITE_POSITION = Position.of('d', '1');

    public Queen(Position position, String name) {
        super(position, name);
    }

    @Override
    void move(Position target, CurrentPieces currentPieces) {

    }

    public static List<Queen> generate() {
        List<Queen> queens = new ArrayList();
        queens.add(new Queen(INITIAL_BLACK_POSITION, "Q"));
        queens.add(new Queen(INITIAL_WHITE_POSITION, "q"));
        return queens;
    }
}
