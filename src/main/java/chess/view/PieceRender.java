package chess.view;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.UnMovablePiece;

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
        throw new AssertionError();
    }

    public static String renderName(final Piece piece) {
        if (piece.getClass() == Pawn.class) {
            return renderByColor(piece.getColor(), "p");
        }

        if (piece.getClass() == Rook.class) {
            return renderByColor(piece.getColor(), "r");
        }

        if (piece.getClass() == Knight.class) {
            return renderByColor(piece.getColor(), "n");
        }

        if (piece.getClass() == Bishop.class) {
            return renderByColor(piece.getColor(), "b");
        }

        if (piece.getClass() == Queen.class) {
            return renderByColor(piece.getColor(), "q");
        }

        if (piece.getClass() == King.class) {
            return renderByColor(piece.getColor(), "k");
        }

        if (piece.getClass() == UnMovablePiece.class) {
            return ".";
        }

        throw new AssertionError();
    }

    private static String renderByColor(final Color color, final String name) {
        if (color.isBlack()) {
            return name.toUpperCase();
        }

        if (color.isWhite()) {
            return name;
        }

        throw new AssertionError();
    }
}
