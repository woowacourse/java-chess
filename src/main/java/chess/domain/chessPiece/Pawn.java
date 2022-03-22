package chess.domain.chessPiece;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements ChessPiece {

    private static final String NAME = "P";
    private static final Pawn INSTANCE = new Pawn();

    private Pawn() {
    }

    public static Pawn getInstance() {
        return INSTANCE;
    }

    @Override
    public List<String> getInitPosition() {
        List<String> list = new ArrayList<>();
        for (int i = 'a'; i <= 'h'; i++) {
            list.add((char) i + "2");
            list.add((char) i + "7");
        }

        return list;
    }
}
