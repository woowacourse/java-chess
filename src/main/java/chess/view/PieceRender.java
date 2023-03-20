package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class PieceRender {

    private static final Map<PieceType, String> CACHE = Arrays.stream(PieceType.values())
            .collect(Collectors.toMap(Function.identity(), PieceType::getValue));

    private PieceRender() {
        throw new AssertionError();
    }

    public static String renderName2(final PieceType pieceType, final Color color) {
        if (color.isBlack()) {
            return CACHE.get(pieceType);
        }
        if (color.isWhite()) {
            return CACHE.get(pieceType).toLowerCase();
        }
        if (color.isEmpty()) {
            return CACHE.get(pieceType);
        }
        throw new AssertionError();
    }
}
