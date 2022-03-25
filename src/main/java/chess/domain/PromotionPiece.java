package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.multiple.Bishop;
import chess.domain.piece.multiple.Rook;
import chess.domain.piece.single.King;
import chess.domain.piece.single.Knight;
import java.util.Arrays;
import java.util.function.Function;

public enum PromotionPiece {

    QUEEN("Q", King::new),
    ROOK("R", Rook::new),
    BISHOP("B", Bishop::new),
    KNIGHT("N", Knight::new),
    ;

    private final String value;
    private final Function<Color, Piece> createPieceFunction;

    PromotionPiece(String value, Function<Color, Piece> createPieceFunction) {
        this.value = value;
        this.createPieceFunction = createPieceFunction;
    }

    public static Piece createPromotionPiece(String input, Color color) {
        return Arrays.stream(values())
                .filter(promotion -> promotion.value.equals(input))
                .map(promotion -> promotion.createPieceFunction.apply(color))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("불가능한 프로모션 기물 이름입니다."));
    }
}
