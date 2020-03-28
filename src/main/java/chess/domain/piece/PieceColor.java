package chess.domain.piece;

import java.util.function.UnaryOperator;

import static chess.util.NullValidator.validateNull;

public enum PieceColor {
    WHITE(String::toLowerCase),
    BLACK(String::toUpperCase),
    NONE((name) -> name);

    private UnaryOperator<String> operator;

    PieceColor(UnaryOperator<String> operator) {
        this.operator = operator;
    }

    public String getPieceName(String pieceName) {
        validateNull(pieceName);
        return this.operator.apply(pieceName);
    }

    public boolean isNoneColor() {
        return this == NONE;
    }
}
