package chess.view;

import chess.domain.Color;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceMapper {

    private static final String BLACK_LETTERS = "RNBQKBNRP";
    private static final String WHITE_LETTERS = "rnbqkbnrp";

    private static final List<Piece> BLACK_PIECES =
            List.of(new Rook(Color.BLACK), new Knight(Color.BLACK), new Bishop(Color.BLACK), new Queen(Color.BLACK),
                    new King(Color.BLACK), new Bishop(Color.BLACK), new Knight(Color.BLACK), new Rook(Color.BLACK),
                    new Pawn(Color.BLACK)
            );
    private static final List<Piece> WHITE_PIECES =
            List.of(new Rook(Color.WHITE), new Knight(Color.WHITE), new Bishop(Color.WHITE), new Queen(Color.WHITE),
                    new King(Color.WHITE), new Bishop(Color.WHITE), new Knight(Color.WHITE), new Rook(Color.WHITE),
                    new Pawn(Color.WHITE)
            );

    private static final Map<Piece, Character> PIECE_MAPPER = new HashMap<>();

    static {
        for (int i = 0; i < BLACK_LETTERS.length(); i++) {
            PIECE_MAPPER.put(WHITE_PIECES.get(i), WHITE_LETTERS.charAt(i));
            PIECE_MAPPER.put(BLACK_PIECES.get(i), BLACK_LETTERS.charAt(i));
        }
    }

    public static String getByPiece(Piece piece) {
        return String.valueOf(PIECE_MAPPER.getOrDefault(piece, '.'));
    }
}
