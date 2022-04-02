package chess.domain.piece;

import java.util.function.Function;

public enum PieceColor {

    BLACK(String::toUpperCase) {
        @Override
        public PieceColor enemyColor() {
            return WHITE;
        }
    },
    WHITE(String::toLowerCase) {
        @Override
        public PieceColor enemyColor() {
            return BLACK;
        }
    };

    private final Function<String, String> signatureFunction;

    PieceColor(Function<String, String> signatureFunction) {
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

    public abstract PieceColor enemyColor();
}
