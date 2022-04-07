package chess.web;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.role.Bishop;
import chess.domain.piece.role.King;
import chess.domain.piece.role.Knight;
import chess.domain.piece.role.Pawn;
import chess.domain.piece.role.Queen;
import chess.domain.piece.role.Rook;
import chess.web.dto.PieceDto;
import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private static final Map<String, Piece> CACHE = new HashMap<>();

    static {
        generateAllPieceCases();
    }

    private static void generateAllPieceCases() {
        CACHE.put("white_p", new Piece(Color.WHITE, new Pawn()));
        CACHE.put("white_n", new Piece(Color.WHITE, new Knight()));
        CACHE.put("white_b", new Piece(Color.WHITE, new Bishop()));
        CACHE.put("white_r", new Piece(Color.WHITE, new Rook()));
        CACHE.put("white_q", new Piece(Color.WHITE, new Queen()));
        CACHE.put("white_k", new Piece(Color.WHITE, new King()));

        CACHE.put("black_p", new Piece(Color.BLACK, new Pawn()));
        CACHE.put("black_n", new Piece(Color.BLACK, new Knight()));
        CACHE.put("black_b", new Piece(Color.BLACK, new Bishop()));
        CACHE.put("black_r", new Piece(Color.BLACK, new Rook()));
        CACHE.put("black_q", new Piece(Color.BLACK, new Queen()));
        CACHE.put("black_k", new Piece(Color.BLACK, new King()));
    }

    public static Piece build(PieceDto pieceDto) {
        return CACHE.get(pieceDto.getColor() + "_" + pieceDto.getRole());
    }
}
