package chess.view;

import static chess.domain.chesspiece.Color.BLACK;
import static chess.domain.chesspiece.Color.WHITE;

import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Queen;
import chess.domain.chesspiece.Rook;
import java.util.Arrays;

public enum PieceName {

    KING_BLACK(King.from(BLACK), "K"),
    KING_WHITE(King.from(WHITE), "k"),
    QUEEN_BLACK(Queen.from(BLACK), "Q"),
    QUEEN_WHITE(Queen.from(WHITE), "q"),
    BISHOP_BLACK(Bishop.from(BLACK), "B"),
    BISHOP_WHITE(Bishop.from(WHITE), "b"),
    ROOK_BLACK(Rook.from(BLACK), "R"),
    ROOK_WHITE(Rook.from(WHITE), "r"),
    KNIGHT_BLACK(Knight.from(BLACK), "N"),
    KNIGHT_WHITE(Knight.from(WHITE), "n"),
    PAWN_BLACK(Pawn.from(BLACK), "P"),
    PAWN_WHITE(Pawn.from(WHITE), "p"),
    ;

    private final ChessPiece chessPiece;
    private final String consoleName;

    PieceName(final ChessPiece chessPiece, final String consoleName) {
        this.chessPiece = chessPiece;
        this.consoleName = consoleName;
    }

    public static String findConsoleName(final ChessPiece chessPiece) {
        return Arrays.stream(values())
                .filter(it -> it.chessPiece.equals(chessPiece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 기물이 없습니다."))
                .consoleName;
    }
}
