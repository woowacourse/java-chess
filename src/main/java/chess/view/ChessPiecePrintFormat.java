package chess.view;

import chess.domain.chesspiece.Camp;
import chess.domain.chesspiece.ChessPieceType;
import chess.dto.ChessPieceDto;
import java.util.Arrays;

public enum ChessPiecePrintFormat {

    NONE(ChessPieceType.NONE, "."),
    KING(ChessPieceType.KING, "K"),
    QUEEN(ChessPieceType.QUEEN, "Q"),
    BISHOP(ChessPieceType.BISHOP, "B"),
    KNIGHT(ChessPieceType.KNIGHT, "N"),
    ROOK(ChessPieceType.ROOK, "R"),
    PAWN(ChessPieceType.PAWN, "P");

    private final ChessPieceType type;
    private final String notation;

    ChessPiecePrintFormat(ChessPieceType type, String notation) {
        this.notation = notation;
        this.type = type;
    }

    public static String findChessPieceNotation(ChessPieceDto chessPieceDto) {
        ChessPieceType chessPieceType = chessPieceDto.chessPieceType();
        Camp camp = chessPieceDto.camp();

        return Arrays.stream(ChessPiecePrintFormat.values())
                .filter(chessPieceFormat -> chessPieceFormat.type == chessPieceType)
                .map(chessPieceFormat -> determineNotation(chessPieceFormat, camp))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 체스말이 없습니다."));
    }

    private static String determineNotation(ChessPiecePrintFormat chessPieceFormat, Camp camp) {
        if (camp == Camp.BLACK) {
            return chessPieceFormat.notation;
        }
        return chessPieceFormat.notation.toLowerCase();
    }
}
