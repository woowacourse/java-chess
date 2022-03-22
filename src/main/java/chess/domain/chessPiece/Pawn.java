package chess.domain.chessPiece;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    private static final String NAME = "P";

    public Pawn(Color color) {
        super(color, NAME);
    }

    @Override
    public List<String> getInitWhitePosition() {
        List<String> list = new ArrayList<>();
        for (int i = 'a'; i <= 'h'; i++) {
            list.add((char) i + "2");
        }
        return list;
    }

    @Override
    public List<String> getInitBlackPosition() {
        List<String> list = new ArrayList<>();
        for (int i = 'a'; i <= 'h'; i++) {
            list.add((char) i + "7");
        }
        return list;
    }

}
