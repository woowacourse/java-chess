package dto;

import chess.domain.Square;
import chess.domain.chesspiece.Piece;

public class PieceDto {
    private final String side;
    private final String type;
    private final int rank;
    private final int file;

    private PieceDto(final String side, final String type, final int rank, final int file) {
        this.side = side;
        this.type = type;
        this.rank = rank;
        this.file = file;
    }

    public static PieceDto from(final Piece piece, final Square square) {
        return new PieceDto(piece.getSide(),
                piece.getName(),
                square.getRankPosition(),
                square.getFilePosition());
    }

    public String getSide() {
        return side;
    }

    public String getType() {
        return type;
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}
