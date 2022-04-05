package chess.domain.piece;

import java.util.stream.Stream;

public enum PieceColor {
    BLACK("BLACK"),
    WHITE("WHITE");

    private final String value;

    PieceColor(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }

    public static PieceColor getPieceColor(String pieceColor) {
        return Stream.of(PieceColor.values())
                .filter(value -> value.getValue().equals(pieceColor))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 색상입니다."));
    }
}
