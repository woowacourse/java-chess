package chess.domain.chessPiece;

import java.util.List;

public class Knight implements ChessPiece {

    private static final String NAME = "N";
    private static final Knight INSTANCE = new Knight();

    private Knight() {
    }

    public static Knight getInstance() {
        return INSTANCE;
    }

    @Override
    public List<String> getInitPosition() {
        return List.of("b1","g1","b8","g8");
    }

    @Override
    public String getName() {
        return NAME;
    }
}
