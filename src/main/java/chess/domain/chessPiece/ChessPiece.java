package chess.domain.chessPiece;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChessPiece {
    private final static Map<String, ChessPiece> CHESS_PIECE_CACHE = new HashMap<>();

    private final PieceType pieceType;

    static {
        for (PieceColor pieceColor : PieceColor.values()) {
            King king = new King(pieceColor);
            Queen queen = new Queen(pieceColor);
            Bishop bishop = new Bishop(pieceColor);
            Rook rook = new Rook(pieceColor);
            Knight knight = new Knight(pieceColor);
            Pawn pawn = new Pawn(pieceColor);

            CHESS_PIECE_CACHE.put(king.getName(), new ChessPiece(king));
            CHESS_PIECE_CACHE.put(queen.getName(), new ChessPiece(queen));
            CHESS_PIECE_CACHE.put(bishop.getName(), new ChessPiece(bishop));
            CHESS_PIECE_CACHE.put(rook.getName(), new ChessPiece(rook));
            CHESS_PIECE_CACHE.put(knight.getName(), new ChessPiece(knight));
            CHESS_PIECE_CACHE.put(pawn.getName(), new ChessPiece(pawn));
        }
    }

    private ChessPiece(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public static ChessPiece of(String key) {
        ChessPiece chessPiece = CHESS_PIECE_CACHE.get(key);

        if (Objects.isNull(chessPiece)) {
            throw new IllegalArgumentException();
        }
        return chessPiece;
    }

    @Override
    public String toString() {
        return this.pieceType.getName();
    }
}
