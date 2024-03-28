package chess.model.score;

import chess.model.piece.Type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Score {
    PAWN(1.0, Type.WHITE_PAWN, Type.BLACK_PAWN),
    KNIGHT(2.5, Type.WHITE_KNIGHT, Type.BLACK_KNIGHT),
    BISHOP(3.0, Type.WHITE_BISHOP, Type.BLACK_BISHOP),
    ROOK(5.0, Type.WHITE_ROOK, Type.BLACK_ROOK),
    QUEEN(9.0, Type.WHITE_QUEEN, Type.BLACK_QUEEN),
    KING(0.0, Type.WHITE_KING, Type.BLACK_KING);

    private final double score;
    private final Set<Type> types;

    Score(double score, Type... types) {
        this.score = score;
        this.types = new HashSet<>(List.of(types));
    }

    private boolean containsType(Type type) {
        return types.contains(type);
    }

    private double getScore() {
        return score;
    }

    public static double from(Type type) {
        return Arrays.stream(values())
                .filter(score -> score.containsType(type))
                .findFirst()
                .map(Score::getScore)
                .orElseThrow(() -> new IllegalStateException("점수가 없는 유형입니다."));
    }
}
