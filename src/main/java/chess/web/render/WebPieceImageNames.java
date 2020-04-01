package chess.web.render;

import chess.piece.AbstractPawn;
import chess.piece.Bishop;
import chess.piece.Blank;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.view.RenderStrategy;

import java.util.Arrays;

public enum WebPieceImageNames {
    KING(King.class, (piece) -> piece.isBlack() ? "king_black" : "king_white"),
    QUEEN(Queen.class, (piece) -> piece.isBlack() ? "queen_black" : "queen_white"),
    BISHOP(Bishop.class, (piece) -> piece.isBlack() ? "bishop_black" : "bishop_white"),
    KNIGHT(Knight.class, (piece) -> piece.isBlack() ? "knight_black" : "knight_white"),
    ROOK(Rook.class, (piece) -> piece.isBlack() ? "rook_black" : "rook_white"),
    PAWN(AbstractPawn.class, (piece) -> piece.isBlack() ? "pawn_black" : "pawn_white"),
    BLANK(Blank.class, (piece -> "blank"));

    private final Class<? extends Piece> pieceClass;
    private final RenderStrategy renderStrategy;

    WebPieceImageNames(final Class<? extends Piece> pieceClass, final RenderStrategy renderStrategy) {
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
