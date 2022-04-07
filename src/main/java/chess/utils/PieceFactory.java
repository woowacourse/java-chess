package chess.utils;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private static final Map<String, Piece> factory = new HashMap<>();

    static {
        factory.put("white_pawn", new Pawn(Color.WHITE));
        factory.put("white_knight", new Knight(Color.WHITE));
        factory.put("white_bishop", new Bishop(Color.WHITE));
        factory.put("white_rook", new Rook(Color.WHITE));
        factory.put("white_queen", new Queen(Color.WHITE));
        factory.put("white_king", new King(Color.WHITE));

        factory.put("black_pawn", new Pawn(Color.BLACK));
        factory.put("black_knight", new Knight(Color.BLACK));
        factory.put("black_bishop", new Bishop(Color.BLACK));
        factory.put("black_rook", new Rook(Color.BLACK));
        factory.put("black_queen", new Queen(Color.BLACK));
        factory.put("black_king", new King(Color.BLACK));

        factory.put("empty", EmptyPiece.getInstance());
    }

    private PieceFactory() {
    }

    public static Piece convertToPiece(String pieceName) {
        if (!factory.containsKey(pieceName)) {
            throw new IllegalStateException("잘못된 pieceName이 들어왔습니다.");
        }
        return factory.get(pieceName);
    }
}
