package chess.view;

import chess.board.piece.Bishop;
import chess.board.piece.King;
import chess.board.piece.Knight;
import chess.board.piece.Pawn;
import chess.board.piece.Piece;
import chess.board.piece.Queen;
import chess.board.piece.Rook;

import java.util.Arrays;

public enum PieceRender {
    KING(King.class, (piece) -> piece.isBlack() ? "K" : "k"),
    QUEEN(Queen.class, (piece) -> piece.isBlack() ? "Q" : "q"),
    BISHOP(Bishop.class, (piece) -> piece.isBlack() ? "B" : "b"),
    KNIGHT(Knight.class, (piece) -> piece.isBlack() ? "N" : "n"),
    ROOK(Rook.class, (piece) -> piece.isBlack() ? "R" : "r"),
    PAWN(Pawn.class, (piece) -> piece.isBlack() ? "P" : "p");

    private final Class<? extends Piece> pieceClass;
    private final RenderStrategy renderStrategy;

    PieceRender(final Class<? extends Piece> pieceClass, final RenderStrategy renderStrategy) {
        this.pieceClass = pieceClass;
        this.renderStrategy = renderStrategy;
    }

    public static String findTokenByPiece(Piece piece) {
        if (piece == null) {
            return ".";
        }

        return Arrays.stream(values())
                .filter(render -> render.pieceClass.equals(piece.getClass()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .renderStrategy.render(piece);
    }
}
