package chess.domain;

import java.util.function.Function;

public enum Color {

    BLACK(String::toUpperCase) {
        @Override
        public Color next() {
            return WHITE;
        }
    },
    WHITE(String::toLowerCase) {
        @Override
        public Color next() {
            return BLACK;
        }
    },
    NONE(signature -> signature) {
        @Override
        public Color next() {
            return NONE;
        }
    };

    private final Function<String, String> signatureFunction;

    Color(Function<String, String> signatureFunction) {
        this.signatureFunction = signatureFunction;
    }

    public String correctSignature(String signature) {
        return signatureFunction.apply(signature);
    }

    public abstract Color next();
}
