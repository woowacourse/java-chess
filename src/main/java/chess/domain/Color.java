package chess.domain;

import java.util.function.Function;

public enum Color {

    BLACK(String::toUpperCase) {
        @Override
        public Color enemyColor() {
            return WHITE;
        }
    },
    WHITE(String::toLowerCase) {
        @Override
        public Color enemyColor() {
            return BLACK;
        }
    };

    private final Function<String, String> signatureFunction;

    Color(Function<String, String> signatureFunction) {
        this.signatureFunction = signatureFunction;
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

    public abstract Color enemyColor();
}
