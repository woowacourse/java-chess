package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.multiple.Bishop;
import chess.domain.piece.multiple.Queen;
import chess.domain.piece.multiple.Rook;
import chess.domain.piece.single.Knight;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public enum PromotionPiece {

    QUEEN("Q", color -> new Piece(color, new Queen())),
    ROOK("R", color -> new Piece(color, new Rook())),
    BISHOP("B", color -> new Piece(color, new Bishop())),
    KNIGHT("N", color -> new Piece(color, new Knight())),
    ;

    private final String value;
    private final Function<Color, Piece> createPieceFunction;

    PromotionPiece(String value, Function<Color, Piece> createPieceFunction) {
        this.value = value;
        this.createPieceFunction = createPieceFunction;
    }

    public static PromotionPiece createPromotionPiece(String input) {
        Objects.requireNonNull(input, "input은 null이 들어올 수 없습니다.");
        return Arrays.stream(values())
                .filter(promotion -> promotion.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("불가능한 프로모션 기물 이름입니다."));
    }

    public Piece createPiece(Color color) {
        return createPieceFunction.apply(color);
    }
}
