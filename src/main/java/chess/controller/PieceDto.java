package chess.controller;

import chess.chessboard.Side;
import chess.piece.*;

public class PieceDto {
    private final String team;
    private final String type;

    private PieceDto(final String team, final String type) {
        this.team = team;
        this.type = type;
    }

    public static PieceDto from(final Piece piece) {
        String type = ".";
        final Side side = piece.getSide();

        if (piece instanceof Pawn) {
            type = "p";
        }
        if (piece instanceof Knight) {
            type = "k";
        }
        if (piece instanceof Bishop) {
            type = "b";
        }
        if (piece instanceof Rook) {
            type = "r";
        }
        if (piece instanceof Queen) {
            type = "q";
        }
        if (piece instanceof King) {
            type = "k";
        }

        return new PieceDto(side.toString(), type);
    }

    public String getSide() {
        return team;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
