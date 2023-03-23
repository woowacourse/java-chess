package techcourse.fp.chess.view;

import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Empty;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.ordinary.Bishop;
import techcourse.fp.chess.domain.piece.ordinary.King;
import techcourse.fp.chess.domain.piece.ordinary.Knight;
import techcourse.fp.chess.domain.piece.ordinary.Queen;
import techcourse.fp.chess.domain.piece.ordinary.Rook;
import techcourse.fp.chess.domain.piece.pawn.BlackPawn;
import techcourse.fp.chess.domain.piece.pawn.WhitePawn;

public final class PieceRender {

    private PieceRender() {
        throw new AssertionError();
    }

    public static String renderName(final Piece piece) {
        if (piece.getClass() == BlackPawn.class) {
            return "P";
        }

        if (piece.getClass() == WhitePawn.class) {
            return "p";
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

        if (piece.getClass() == Empty.class) {
            return ".";
        }

        throw new IllegalStateException("매핑되지 않는 기물이 존재합니다.");
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
