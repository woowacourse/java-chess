package chess.domain.chessPiece;

import java.util.List;

public class Bishop extends ChessPiece {

    private static final String NAME = "B";

    public Bishop(Color color) {
        super(color, NAME);
    }

    @Override
    public List<String> getInitWhitePosition() {
        return List.of("c1", "f1");
    }

    @Override
    public List<String> getInitBlackPosition() {
        return List.of("c8", "f8");
    }


}
