package chess.domain.chessPiece;

import chess.domain.chessPiece.pieceType.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ChessPieceCache {
    private static Map<String, Supplier<ChessPiece>> chessPieceCache = new HashMap<>();

    static {
        chessPieceCache.put("p", () -> new Pawn(PieceColor.BLACK));
        chessPieceCache.put("r", () -> new Rook(PieceColor.BLACK));
        chessPieceCache.put("n", () -> new Knight(PieceColor.BLACK));
        chessPieceCache.put("b", () -> new Bishop(PieceColor.BLACK));
        chessPieceCache.put("q", () -> new Queen(PieceColor.BLACK));
        chessPieceCache.put("k", () -> new King(PieceColor.BLACK));
        chessPieceCache.put("P", () -> new Pawn(PieceColor.WHITE));
        chessPieceCache.put("R", () -> new Rook(PieceColor.WHITE));
        chessPieceCache.put("N", () -> new Knight(PieceColor.WHITE));
        chessPieceCache.put("B", () -> new Bishop(PieceColor.WHITE));
        chessPieceCache.put("Q", () -> new Queen(PieceColor.WHITE));
        chessPieceCache.put("K", () -> new King(PieceColor.WHITE));
    }

    public static ChessPiece getChessPiece(String position,String piece) {
        Supplier<ChessPiece> chessPieceSupplier = chessPieceCache.get(piece);
        ChessPiece chessPiece = chessPieceSupplier.get();

        if (chessPiece instanceof Pawn) {
            ((Pawn) chessPiece).pawnStateSelector(position);
        }
        return chessPiece;
    }
}
