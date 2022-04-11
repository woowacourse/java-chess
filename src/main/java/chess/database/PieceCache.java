package chess.database;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import chess.database.dto.PieceDto;
import chess.domain.Color;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class PieceCache {

    private static final Map<Color, Map<String, Piece>> PIECE_CACHE =
        Map.of(Color.WHITE, createPieces(Color.WHITE),
            Color.BLACK, createPieces(Color.BLACK));

    private static Map<String, Piece> createPieces(Color color) {
        Map<String, Piece> pieces = new HashMap<>();
        pieces.put("BISHOP", new Bishop(color));
        pieces.put("KING", new King(color));
        pieces.put("KNIGHT", new Knight(color));
        pieces.put("QUEEN", new Queen(color));
        pieces.put("ROOK", new Rook(color));
        pieces.put("PAWN", new Pawn(color));
        return pieces;
    }

    public static Piece getPiece(String name, String color) {
        Map<String, Piece> pieces = PIECE_CACHE.get(Color.of(color));
        if (!pieces.containsKey(name)) {
            throw new IllegalArgumentException("[ERROR] 해당하는 말이 없습니다.");
        }
        return pieces.get(name.toUpperCase(Locale.ROOT));
    }

    public static Piece getPiece(PieceDto pieceDto) {
        return getPiece(pieceDto.getType(), pieceDto.getColor());
    }
}
