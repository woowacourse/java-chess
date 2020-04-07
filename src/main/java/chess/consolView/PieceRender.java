package chess.consolView;

import chess.domain.piece.*;

import java.util.Arrays;

public enum PieceRender {
    KING(King.class, (piece) -> piece.isBlack() ? "♚" : "♔"),
    QUEEN(Queen.class, (piece) -> piece.isBlack() ? "♛" : "♕"),
    BISHOP(Bishop.class, (piece) -> piece.isBlack() ? "♝" : "♗"),
    KNIGHT(Knight.class, (piece) -> piece.isBlack() ? "♞" : "♘"),
    ROOK(Rook.class, (piece) -> piece.isBlack() ? "♜" : "♖"),
    PAWN(AbstractPawn.class, (piece) -> piece.isBlack() ? "♟" : "♙"),
    BLANK(Blank.class, (piece -> ""));

    private final Class<? extends Piece> pieceClass;
    private final RenderStrategy renderStrategy;

    PieceRender(final Class<? extends Piece> pieceClass, final RenderStrategy renderStrategy) {
        this.pieceClass = pieceClass;
        this.renderStrategy = renderStrategy;
    }

    public static String findTokenByPiece(Piece piece) {
        return Arrays.stream(values())
                .filter(render -> render.isSameType(piece))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .renderStrategy.render(piece);
    }

    private boolean isSameType(Piece piece) {
        if (this.pieceClass.equals(piece.getClass())) {
            return true;
        }
        return this.pieceClass.equals(piece.getClass().getSuperclass());
    }
}
