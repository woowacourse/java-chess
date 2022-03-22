package chess.domain.chessPiece;

import java.util.List;

public class King extends ChessPiece {

    private static final String NAME = "K";

    public King(Color color) {
        super(color, NAME);
    }

    @Override
    public List<String> getInitWhitePosition() {
        return List.of("e1");
    }

    @Override
    public List<String> getInitBlackPosition() {
        return List.of("e8");
    }

}
