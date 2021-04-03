package chess.domain.piece.type;

import static chess.domain.piece.type.PieceType.BISHOP;
import static chess.domain.piece.type.PieceType.KING;
import static chess.domain.piece.type.PieceType.KNIGHT;
import static chess.domain.piece.type.PieceType.PAWN;
import static chess.domain.piece.type.PieceType.QUEEN;
import static chess.domain.piece.type.PieceType.ROOK;
import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.domain.player.type.TeamColor;
import java.util.Arrays;

public enum PieceWithColorType {
    B_PN(PAWN, BLACK),
    B_RK(ROOK, BLACK),
    B_BP(BISHOP, BLACK),
    B_NT(KNIGHT, BLACK),
    B_QN(QUEEN, BLACK),
    B_KG(KING, BLACK),
    W_PN(PAWN, WHITE),
    W_RK(ROOK, WHITE),
    W_BP(BISHOP, WHITE),
    W_NT(KNIGHT, WHITE),
    W_QN(QUEEN, WHITE),
    W_KG(KING, WHITE);

    private final PieceType pieceType;
    private final TeamColor teamColor;

    PieceWithColorType(PieceType pieceType, TeamColor teamColor) {
        this.pieceType = pieceType;
        this.teamColor = teamColor;
    }

    public static PieceWithColorType of(String pieceName, String pieceColor) {
        PieceType pieceType = PieceType.valueOf(pieceName);
        TeamColor teamColor = TeamColor.of(pieceColor);
        return Arrays.stream(PieceWithColorType.values())
            .filter(pieceWithColorType ->
                pieceWithColorType.getPieceType() == pieceType
                    && pieceWithColorType.getTeamColor() == teamColor)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 색깔기물 타입입니다."));
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }
}
