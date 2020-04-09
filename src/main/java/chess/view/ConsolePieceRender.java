package chess.view;

import chess.piece.AbstractPawn;
import chess.piece.Bishop;
import chess.piece.Blank;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;

import java.util.Arrays;

public enum ConsolePieceRender {
    KING(King.class, (piece) -> piece.isBlack() ? "K" : "k"),
    QUEEN(Queen.class, (piece) -> piece.isBlack() ? "Q" : "q"),
    BISHOP(Bishop.class, (piece) -> piece.isBlack() ? "B" : "b"),
    KNIGHT(Knight.class, (piece) -> piece.isBlack() ? "N" : "n"),
    ROOK(Rook.class, (piece) -> piece.isBlack() ? "R" : "r"),
    PAWN(AbstractPawn.class, (piece) -> piece.isBlack() ? "P" : "p"),
    BLANK(Blank.class, (piece -> "."));

    private final Class<? extends Piece> pieceClass;
    private final RenderStrategy renderStrategy;

    ConsolePieceRender(final Class<? extends Piece> pieceClass, final RenderStrategy renderStrategy) {
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
