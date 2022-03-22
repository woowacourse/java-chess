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
    public List<String> getInitWhitePosition() {
        return List.of("d1");
    }

    @Override
    public List<String> getInitBlackPosition() {
        return List.of("d8");
    }

    @Override
    public String getName() {
        return NAME;
    }
}
