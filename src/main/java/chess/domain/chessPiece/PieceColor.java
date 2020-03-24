package chess.domain.chessPiece;

import java.util.function.Function;

public enum PieceColor {
    WHITE(String::toLowerCase),
    BLACK(String::toUpperCase);

    private final Function<String, String> convertName;

    PieceColor(Function<String, String> convertName) {
        this.convertName = convertName;
    }

    public String convertName(String pieceName) {
        return convertName.apply(pieceName);
    }
}
