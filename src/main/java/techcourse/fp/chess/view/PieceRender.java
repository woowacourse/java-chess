package techcourse.fp.chess.view;

import techcourse.fp.chess.domain.piece.Bishop;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.King;
import techcourse.fp.chess.domain.piece.Knight;
import techcourse.fp.chess.domain.piece.Pawn;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.Queen;
import techcourse.fp.chess.domain.piece.Rook;
import techcourse.fp.chess.domain.piece.UnMovablePiece;

public final class PieceRender {

    private PieceRender() {
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
