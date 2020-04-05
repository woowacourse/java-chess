package chess.domain.piece;

import chess.exception.ColorNotFoundException;

import java.util.Arrays;
import java.util.function.UnaryOperator;

import static chess.util.NullValidator.validateNull;

public enum PieceColor {
    WHITE("White", String::toLowerCase),
    BLACK("Black", String::toUpperCase),
    NONE("None", (name) -> name);

    private String name;
    private UnaryOperator<String> operator;

    PieceColor(String name, UnaryOperator<String> operator) {
        this.name = name;
        this.operator = operator;
    }

    public static PieceColor of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getPieceName(String pieceName) {
        validateNull(pieceName);
        return this.operator.apply(pieceName);
    }

    public boolean isNoneColor() {
        return this == NONE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public PieceColor change() {
        if (this == NONE) {
            throw new ColorNotFoundException("존재하지 않는 색입니다.");
        }
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public String getName() {
        return name;
    }
}
