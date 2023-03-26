package chess.domain.piece;

import java.util.Arrays;

public enum PieceType {
    QUEEN(Score.create(9)),
    ROOK(Score.create(5)),
    KNIGHT(Score.create(2.5)),
    PAWN(Score.create(1)),
    BISHOP(Score.create(3)),
    KING(Score.create(0));

    private static final String ERROR_MESSAGE = "일치하는 체스 말의 타입이 존재하지 않습니다.";

    private final Score score;

    PieceType(final Score score) {
        this.score = score;
    }

    public static PieceType from(final String name) {
        return Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType.name().equals(name))
                .findFirst()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(ERROR_MESSAGE);
                });
    }

    public Score getScore() {
        return score;
    }
}
