package chess.domain.piece;

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
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public String getName() {
        return name;
    }
}
