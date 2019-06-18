package chess.domain;

public class ChessPiecesBuilder {
    private static ChessPiecesBuilder builder = null;

    private ChessPiecesBuilder() {
    }

    public static ChessPiecesBuilder getInstance() {
        if (builder == null) {
            builder = new ChessPiecesBuilder();
        }
        return builder;
    }
}
