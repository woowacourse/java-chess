package chess.domain.score;

import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum Score {

    KING_SCORE((pieces) -> pieces.size() > 0
            && pieces.stream().filter(Piece::isKing).count() == pieces.size(), 0.0),
    QUEEN_SCORE((pieces) -> pieces.size() > 0
            && pieces.stream().filter(Piece::isQueen).count() == pieces.size(), 9.0),
    ROOK_SCORE((pieces) -> pieces.size() > 0
            && pieces.stream().filter(Piece::isRook).count() == pieces.size(), 5.0),
    BISHOP_SCORE((pieces) -> pieces.size() > 0
            && pieces.stream().filter(Piece::isBishop).count() == pieces.size(), 3.0),
    KNIGHT_SCORE((pieces) -> pieces.size() > 0
            && pieces.stream().filter(Piece::isKnight).count() == pieces.size(), 2.5),
    PAWN_SINGLE_COLUMN_SCORE((pieces) -> pieces.size() > 1
            && pieces.stream().filter(Piece::isPawn).count() == pieces.size(), 0.5),
    PAWN_MULTIPLE_COLUMN_SCORE((pieces) -> pieces.size() == 1
            && pieces.stream().filter(Piece::isPawn).count() == pieces.size(), 1.0);

    private final Predicate<List<Piece>> condition;
    private final double score;

    Score(Predicate<List<Piece>> condition, double score) {
        this.condition = condition;
        this.score = score;
    }

    public static double from(List<Piece> pieces) {
        return Arrays.stream(Score.values())
                    .filter(eachEnum -> eachEnum.condition.test(pieces))
                    .mapToDouble(eachEnum -> eachEnum.score)
                    .findFirst()
                    .orElse(0.0);
    }
}
