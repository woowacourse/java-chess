package domain.piece.type;

import static domain.piece.Score.BISHOP_SCORE;
import static domain.piece.Score.KNIGHT_SCORE;
import static domain.piece.Score.PAWN_SCORE;
import static domain.piece.Score.QUEEN_SCORE;
import static domain.piece.Score.ROOK_SCORE;
import static domain.piece.Score.ZERO_SCORE;

import java.util.Arrays;
import java.util.function.Function;

import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.Score;
import domain.piece.type.restricted.King;
import domain.piece.type.restricted.Knight;
import domain.piece.type.unrestricted.Bishop;
import domain.piece.type.unrestricted.Queen;
import domain.piece.type.unrestricted.Rook;

public enum Type {
    KING('k', ZERO_SCORE, King::new),
    QUEEN('q', QUEEN_SCORE, Queen::new),
    ROOK('r', ROOK_SCORE, Rook::new),
    BISHOP('b', BISHOP_SCORE, Bishop::new),
    KNIGHT('n', KNIGHT_SCORE, Knight::new),
    PAWN('p', PAWN_SCORE, Pawn::new),
    EMPTY('.', ZERO_SCORE, (ignored) -> Empty.getInstance());

    private final char label;
    private final Score score;
    private final Function<Camp, Piece> expression;

    Type(char label, Score score, Function<Camp, Piece> expression) {
        this.label = label;
        this.score = score;
        this.expression = expression;
    }

    public Piece createPiece(Camp camp) {
        return expression.apply(camp);
    }

    public char getLabel() {
        return label;
    }

    public Score getScore() {
        return score;
    }

    public static Type find(String piece) {
        return Arrays.stream(Type.values())
                .filter(type -> type.name().equals(piece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물 입니다."));
    }
}
