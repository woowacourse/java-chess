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
        if (pieceName == null || pieceName.isEmpty()) {
            throw new IllegalArgumentException("체스 이름이 유효하지 않습니다.");
        }
        return convertName.apply(pieceName);
    }
}
