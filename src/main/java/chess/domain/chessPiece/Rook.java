package chess.domain.chessPiece;

import java.util.List;

public class Rook extends ChessPiece {

    private static final String NAME = "R";

    public Rook(Color color) {
        super(color, NAME);
    }

    @Override
    public List<String> getInitWhitePosition() {
        return List.of("a1", "h1");
    }

    @Override
    public List<String> getInitBlackPosition() {
        return List.of("a8", "h8");
    }

}
