package chess.domain.piece.dto;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.*;

public class SavePieceDto {

    private static final Map<Class<? extends Piece>, String> typeByPiece = new HashMap<>();

    static {
        typeByPiece.put(King.class, "KING");
        typeByPiece.put(Queen.class, "QUEEN");
        typeByPiece.put(Bishop.class, "BISHOP");
        typeByPiece.put(Knight.class, "KNIGHT");
        typeByPiece.put(Rook.class, "ROOK");
        typeByPiece.put(Pawn.class, "PAWN");
    }

    private String rank;
    private String file;
    private String type;
    private String side;
    private Long gameId;

    public SavePieceDto(Piece piece, Long gameId) {
        this.rank = String.valueOf(piece.getRank());
        this.file = String.valueOf(piece.getFile());
        this.type = typeByPiece.get(piece.getClass());
        this.side = piece.getSide().getDisplayName();
        this.gameId = gameId;
    }

    public String getRank() {
        return rank;
    }

    public String getFile() {
        return file;
    }

    public String getType() {
        return type;
    }

    public String getSide() {
        return side;
    }

    public Long getGameId() {
        return gameId;
    }
}
