package chess.beforedb.domain.piece.type;

import static chess.beforedb.domain.piece.type.PieceType.BISHOP;
import static chess.beforedb.domain.piece.type.PieceType.KING;
import static chess.beforedb.domain.piece.type.PieceType.KNIGHT;
import static chess.beforedb.domain.piece.type.PieceType.PAWN;
import static chess.beforedb.domain.piece.type.PieceType.QUEEN;
import static chess.beforedb.domain.piece.type.PieceType.ROOK;
import static chess.beforedb.domain.player.type.TeamColor.BLACK;
import static chess.beforedb.domain.player.type.TeamColor.WHITE;

import chess.beforedb.domain.player.type.TeamColor;

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

    public PieceType type() {
        return pieceType;
    }

    public TeamColor color() {
        return teamColor;
    }
}
