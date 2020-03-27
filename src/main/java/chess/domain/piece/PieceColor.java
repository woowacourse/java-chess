package chess.domain.piece;

import java.util.Objects;
import java.util.function.UnaryOperator;

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

    private void validateNull(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("null이 들어왔습니다.");
        }
    }
}
