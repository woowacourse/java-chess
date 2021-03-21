package chess.domain.piece.type;

import static chess.domain.player.type.TeamColor.WHITE;

import chess.domain.player.type.TeamColor;

public enum PieceType {
    PAWN("P"),
    ROOK("R"),
    BISHOP("B"),
    KNIGHT("N"),
    QUEEN("Q"),
    KING("K");

    private final String name;

    PieceType(String name) {
        this.name = name;
    }

    public String name(TeamColor teamColor) {
        if (teamColor == WHITE) {
            return name.toLowerCase();
        }
        return name;
    }
}
