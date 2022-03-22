package chess.domain.chessPiece;

import java.util.List;

public class Knight extends ChessPiece {

    private static final String NAME = "N";

    public Knight(Color color) {
        super(color, NAME);
    }

    @Override
    public List<String> getInitWhitePosition() {
        return List.of("b1", "g1");
    }

    @Override
    public List<String> getInitBlackPosition() {
        return List.of("b8", "g8");
    }

}
