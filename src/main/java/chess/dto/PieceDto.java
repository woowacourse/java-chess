package chess.dto;

import chess.piece.Piece;

import java.util.Map;
import java.util.function.Predicate;

public class PieceDto {

    private final String view;
    private final Map<Predicate<Piece>, String> views = Map.of(
            Piece::isKing, "k",
            Piece::isQueen, "q",
            Piece::isKnight, "n",
            Piece::isPawn, "p",
            Piece::isRook, "r",
            Piece::isBishop, "b",
            Piece::isEmpty, "."
    );

    public PieceDto(final Piece piece) {
        this.view = parseByTeam(piece);
    }

    private String parseByTeam(final Piece piece2) {
        String result = views.entrySet().stream()
                .filter(piece -> piece.getKey().test(piece2))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IllegalStateException("Piece 변환 과정 중 오류가 발생했습니다."));

        if (piece2.isBlack()) {
            return result.toUpperCase();
        }
        return result;
    }

    public String getView() {
        return view;
    }
}
