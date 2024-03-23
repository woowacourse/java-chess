package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.Type;
import java.util.Arrays;

public enum PieceView {
    KING('K'),
    QUEEN('Q'),
    PAWN('P'),
    ROOK('R'),
    BISHOP('B'),
    KNIGHT('N');

    public static final String INVALID_TYPE = "일치하는 타입이 없습니다.";

    private final char display;

    PieceView(final char display) {
        this.display = display;
    }

    public static PieceView findByType(final Type type) {
        return Arrays.stream(PieceView.values())
                .filter(value -> value.name().equals(type.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_TYPE));
    }

    public char changeToView(final Color color) {
        if (Color.WHITE.isSame(color)) {
            return Character.toLowerCase(display);
        }
        return display;
    }
}
