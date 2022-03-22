package chess.domain.chessPiece;

import java.util.List;

public class King implements ChessPiece {

    private static final String NAME = "K";
    private static final King INSTANCE = new King();

    private King() {
    }

    public static King getInstance() {
        return INSTANCE;
    }

    @Override
    public List<String> getInitPosition() {
        return List.of("e1","e8");
    }

    @Override
    public String getName() {
        return NAME;
    }
}
