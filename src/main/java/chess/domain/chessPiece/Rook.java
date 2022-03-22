package chess.domain.chessPiece;

import java.util.List;

public class Rook implements ChessPiece {

    private static final String NAME = "R";
    private static final Rook INSTANCE = new Rook();

    private Rook() {
    }

    public static Rook getInstance() {
        return INSTANCE;
    }

    @Override
    public List<String> getInitPosition() {
        return List.of("a1", "h1", "a8", "h8");
    }
}
