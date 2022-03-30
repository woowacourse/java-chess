package chess.domain;

import java.util.Locale;

public enum Color {

    BLACK {
        @Override
        public Color next() {
            return WHITE;
        }

        @Override
        public String correctSignature(String signature) {
            return signature.toUpperCase(Locale.ROOT);

        }
    },
    WHITE {
        @Override
        public Color next() {
            return BLACK;
        }

        @Override
        public String correctSignature(String signature) {
            return signature.toLowerCase(Locale.ROOT);
        }
    },
    NONE {
        @Override
        public Color next() {
            return NONE;
        }

        @Override
        public String correctSignature(String signature) {
            return signature;
        }
    };

    public abstract Color next();

    public abstract String correctSignature(String signature);
}
