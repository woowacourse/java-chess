package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

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

    public static String renderName(final Piece piece) {
        final Team team = piece.getColor();
        final PieceType pieceType = piece.getPieceType();

        if (team.isBlack()) {
            return CACHE.get(pieceType);
        }
        if (team.isWhite()) {
            return CACHE.get(pieceType).toLowerCase();
        }
        if (team.isEmpty()) {
            return CACHE.get(pieceType);
        }
        throw new AssertionError();
    }
}
