package chess.domain.piece.type;

import static chess.domain.player.type.TeamColor.WHITE;

import chess.domain.player.type.TeamColor;
import java.util.Arrays;

public enum PieceType {
    PAWN("P", "chess.domain.piece.Pawn"),
    ROOK("R", "chess.domain.piece.Rook"),
    BISHOP("B", "chess.domain.piece.Bishop"),
    KNIGHT("N", "chess.domain.piece.Knight"),
    QUEEN("Q", "chess.domain.piece.Queen"),
    KING("K", "chess.domain.piece.King");

    private final String name;
    private final String className;

    PieceType(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public static PieceType find(String name) {
        return Arrays.stream(PieceType.values())
            .filter(pieceType -> pieceType.name().equals(name.toUpperCase()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물 타입입니다."));
    }

    public String getName(TeamColor teamColor) {
        if (teamColor == WHITE) {
            return name.toLowerCase();
        }
        return name;
    }

    public static String getClassNameByPieceType(PieceType pieceType) {
        return Arrays.stream(PieceType.values())
            .filter(type -> type == pieceType)
            .findAny()
            .map(type -> type.className)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물 타입 입니다."));
    }
}
