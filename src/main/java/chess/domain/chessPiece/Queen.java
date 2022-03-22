package chess.domain.chessPiece;

import java.util.List;

public class Queen implements ChessPiece {

    private static final String NAME = "Q";
    private static final Queen INSTANCE = new Queen();

    private Queen() {
    }

    public static Queen getInstance() {
        return INSTANCE;
    }

    @Override
    public List<String> getInitPosition() {
        return List.of("d1", "d8");
    }
}
