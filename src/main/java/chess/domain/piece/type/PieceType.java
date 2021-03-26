package chess.domain.piece.type;

import static chess.domain.player.type.TeamColor.WHITE;

import chess.domain.player.type.TeamColor;
import java.util.Arrays;

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

    public static PieceType find(String name) {
        return Arrays.stream(PieceType.values())
            .filter(pieceType -> pieceType.name().equals(name.toUpperCase()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물 타입입니다."));
    }

    public String name(TeamColor teamColor) {
        if (teamColor == WHITE) {
            return name.toLowerCase();
        }
        return name;
    }
}
