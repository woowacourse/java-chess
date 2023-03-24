package chess.domain.piece;

import chess.domain.Score;
import java.util.Arrays;

public enum PieceType {

    PAWN(Score.from(1)),
    KING(Score.from(0)),
    QUEEN(Score.from(9)),
    KNIGHT(Score.from(2.5)),
    BISHOP(Score.from(3)),
    ROOK(Score.from(5));

    private final Score score;

    PieceType(final Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }

    public static PieceType findByName(final String name) {
        return Arrays.stream(values())
            .filter(type -> type.name().equalsIgnoreCase(name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입입니다."));
    }

}
