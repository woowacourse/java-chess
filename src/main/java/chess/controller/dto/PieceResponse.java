package chess.controller.dto;

import chess.domain.game.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class PieceResponse {

    private final int fileIndex;
    private final int rankIndex;
    private final boolean isWhite;
    private final String type;

    public PieceResponse(int fileIndex, int rankIndex, boolean isWhite, String type) {
        this.fileIndex = fileIndex;
        this.rankIndex = rankIndex;
        this.isWhite = isWhite;
        this.type = type;
    }

    public static PieceResponse of(Position position, Piece piece) {
        int fileIndex = position.getFileIndex();
        int rankIndex = position.getRankIndex();
        boolean isWhite = piece.hasTeam(Team.WHITE);
        String pieceType = piece.getType().name();
        return new PieceResponse(fileIndex, rankIndex, isWhite, pieceType);
    }

    public int getFileIndex() {
        return fileIndex;
    }

    public int getRankIndex() {
        return rankIndex;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public String getType() {
        return type;
    }
}
