package chess.dto;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.attribute.Team;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceDto {
    private static final Map<Name, String> WHITE_WEB_CACHE = new HashMap<>();
    private static final Map<Name, String> BLACK_WEB_CACHE = new HashMap<>();

    private static final Map<String, Piece> DB_CACHE = new HashMap<>();

    static {
        WHITE_WEB_CACHE.put(Name.PAWN, "♙");
        WHITE_WEB_CACHE.put(Name.BISHOP, "♗");
        WHITE_WEB_CACHE.put(Name.KING, "♔");
        WHITE_WEB_CACHE.put(Name.QUEEN, "♕");
        WHITE_WEB_CACHE.put(Name.KNIGHT, "♘");
        WHITE_WEB_CACHE.put(Name.ROOK, "♖");
        WHITE_WEB_CACHE.put(Name.NONE, " ");

        BLACK_WEB_CACHE.put(Name.PAWN, "♟");
        BLACK_WEB_CACHE.put(Name.BISHOP, "♝");
        BLACK_WEB_CACHE.put(Name.KING, "♚");
        BLACK_WEB_CACHE.put(Name.QUEEN, "♛");
        BLACK_WEB_CACHE.put(Name.KNIGHT, "♞");
        BLACK_WEB_CACHE.put(Name.ROOK, "♜");
        BLACK_WEB_CACHE.put(Name.NONE, " ");

        DB_CACHE.put(".", new EmptyPiece());
        DB_CACHE.put("R", new Rook(Team.BLACK));
        DB_CACHE.put("N", new Knight(Team.BLACK));
        DB_CACHE.put("B", new Bishop(Team.BLACK));
        DB_CACHE.put("Q", new Queen(Team.BLACK));
        DB_CACHE.put("K", new King(Team.BLACK));
        DB_CACHE.put("P", new Pawn(Team.BLACK));
        DB_CACHE.put("r", new Rook(Team.WHITE));
        DB_CACHE.put("n", new Knight(Team.WHITE));
        DB_CACHE.put("b", new Bishop(Team.WHITE));
        DB_CACHE.put("q", new Queen(Team.WHITE));
        DB_CACHE.put("k", new King(Team.WHITE));
        DB_CACHE.put("p", new Pawn(Team.WHITE));
    }

    private final Piece piece;

    private final String emoji;

    public PieceDto(Piece piece) {
        this.piece = piece;
        this.emoji = setPiece();
    }

    private String setPiece() {
        if (piece.getTeam() == Team.BLACK) {
            return BLACK_WEB_CACHE.get(piece.getName());
        }
        return WHITE_WEB_CACHE.get(piece.getName());
    }

    public String getEmoji() {
        return emoji;
    }

    public static Piece getPiece(String position) {
        return DB_CACHE.get(position);
    }
}
