package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import java.util.HashMap;
import java.util.Map;

public class PieceDto {
    private static final Map<Name,String> WHITE_CACHE = new HashMap<>();
    private static final Map<Name,String> BLACK_CACHE = new HashMap<>();
    static {
        WHITE_CACHE.put(Name.PAWN, "♙");
        WHITE_CACHE.put(Name.BISHOP, "♗");
        WHITE_CACHE.put(Name.KING, "♔");
        WHITE_CACHE.put(Name.QUEEN, "♕");
        WHITE_CACHE.put(Name.KNIGHT, "♘");
        WHITE_CACHE.put(Name.ROOK, "♖");
        WHITE_CACHE.put(Name.NONE, " ");

        BLACK_CACHE.put(Name.PAWN, "♟");
        BLACK_CACHE.put(Name.BISHOP, "♝");
        BLACK_CACHE.put(Name.KING, "♚");
        BLACK_CACHE.put(Name.QUEEN, "♛");
        BLACK_CACHE.put(Name.KNIGHT, "♞");
        BLACK_CACHE.put(Name.ROOK, "♜");
        BLACK_CACHE.put(Name.NONE, " ");
    }
    private final Piece piece;

    private final String emoji;

    public PieceDto(Piece piece) {
        this.piece = piece;
        this.emoji = setPiece();
    }

    private String setPiece() {
        if (piece.getTeam() == Team.BLACK) {
            return BLACK_CACHE.get(piece.getName());
        }
        return WHITE_CACHE.get(piece.getName());
    }

    public String getEmoji() {
        return emoji;
    }
}
