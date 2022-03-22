package chess.domain.chessPiece;

import java.util.List;

public class Queen extends ChessPiece {

    private static final String NAME = "Q";

    public Queen(Color color) {
        super(color, NAME);
    }

    @Override
    public List<String> getInitWhitePosition() {
        return List.of("d1");
    }

    @Override
    public List<String> getInitBlackPosition() {
        return List.of("d8");
    }

}
