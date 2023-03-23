package chess.domain.dto.res;

import chess.domain.Color;
import chess.domain.Piece;

public class PieceResponse {

    private final int rank;
    private final char file;
    private final char name;

    public PieceResponse(final Piece piece, final Color color) {
        this.rank = piece.getRank();
        this.file = piece.getFile();
        this.name = piece.getName(color);
    }

    public boolean samePosition(final int rank, final int file) {
        return this.rank == rank && this.file == file;
    }

    public String getName() {
        return String.valueOf(this.name);
    }

}
