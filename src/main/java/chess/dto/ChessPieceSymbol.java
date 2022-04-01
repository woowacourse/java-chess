package chess.dto;

import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.function.Function;

public enum ChessPieceSymbol {
    PAWN(Pawn.class::isInstance, 'p'),
    BISHOP(Bishop.class::isInstance, 'b'),
    ROOK(Rook.class::isInstance, 'r'),
    KNIGHT(Knight.class::isInstance, 'n'),
    QUEEN(Queen.class::isInstance, 'q'),
    KING(King.class::isInstance, 'k');

    private static final String NO_MATCHING_CHESS_PIECE_EXCEPTION = "[ERROR] 체스 피스에 해당하는 심볼을 찾을 수 없습니다.";

    private final Function<ChessPiece, Boolean> chessPiece;
    private final char symbol;

    ChessPieceSymbol(Function<ChessPiece, Boolean> chessPiece, char symbol) {
        this.chessPiece = chessPiece;
        this.symbol = symbol;
    }

    public static Character convertToSymbol(ChessPiece chessPiece) {
        ChessPieceSymbol chessPieceSymbol = of(chessPiece);
        if (chessPiece.getTeam().isBlack()) {
            return Character.toUpperCase(chessPieceSymbol.symbol);
        }
        return chessPieceSymbol.symbol;
    }

    private static ChessPieceSymbol of(ChessPiece chessPiece) {
        return Arrays.stream(values())
                .filter(it -> it.chessPiece.apply(chessPiece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCHING_CHESS_PIECE_EXCEPTION));
    }
}
