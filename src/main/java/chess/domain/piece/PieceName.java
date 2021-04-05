package chess.domain.piece;

import chess.dto.response.ResponseCode;
import chess.exception.ChessException;

import java.util.Arrays;

public enum PieceName {
    Bishop('B', 'b', "chess.domain.piece.Bishop"),
    Empty('.', '.', "chess.domain.piece.Empty"),
    King('K', 'k', "chess.domain.piece.King"),
    Knight('N', 'n', "chess.domain.piece.Knight"),
    Pawn('P', 'p', "chess.domain.piece.Pawn"),
    Queen('Q', 'q', "chess.domain.piece.Queen"),
    Rook('R', 'r', "chess.domain.piece.Rook");

    private final char nameWhenBlack;
    private final char nameWhenWhite;
    private final String className;

    PieceName(
            char nameWhenBlack,
            char nameWhenWhite,
            String className
    ) {
        this.nameWhenBlack = nameWhenBlack;
        this.nameWhenWhite = nameWhenWhite;
        this.className = className;
    }

    public static String getClassNameByPieceName(char pieceName) {
        return Arrays.stream(PieceName.values())
                .filter(piece -> (pieceName == piece.nameWhenBlack || pieceName == piece.nameWhenWhite))
                .findFirst()
                .map(piece -> piece.className)
                .orElseThrow(() -> new ChessException(ResponseCode.NOT_EXISTING_PIECE));
    }
}
