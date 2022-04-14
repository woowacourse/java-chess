package chess.domain.piece;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceColor {

    BLACK("black", String::toUpperCase) {
        @Override
        public PieceColor enemyColor() {
            return WHITE;
        }
    },
    WHITE("white", String::toLowerCase) {
        @Override
        public PieceColor enemyColor() {
            return BLACK;
        }
    };

    private final String name;
    private final Function<String, String> signatureFunction;

    PieceColor(String name, Function<String, String> signatureFunction) {
        this.name = name;
        this.signatureFunction = signatureFunction;
    }

    public static PieceColor find(String name) {
        return Arrays.stream(values())
                .filter(color -> name.equals(color.name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 색을 찾을 수 없습니다."));
    }

    public String correctSignature(String signature) {
        return signatureFunction.apply(signature);
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public abstract PieceColor enemyColor();

    public String getName() {
        return name().toLowerCase();
    }
}
