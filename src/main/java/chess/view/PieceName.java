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

    KING_BLACK(King.from(BLACK), "K", "/images/king_black.png"),
    KING_WHITE(King.from(WHITE), "k", "/images/king_white.png"),
    QUEEN_BLACK(Queen.from(BLACK), "Q", "/images/queen_black.png"),
    QUEEN_WHITE(Queen.from(WHITE), "q", "/images/queen_white.png"),
    BISHOP_BLACK(Bishop.from(BLACK), "B", "/images/bishop_black.png"),
    BISHOP_WHITE(Bishop.from(WHITE), "b", "/images/bishop_white.png"),
    ROOK_BLACK(Rook.from(BLACK), "R", "/images/rook_black.png"),
    ROOK_WHITE(Rook.from(WHITE), "r", "/images/rook_white.png"),
    KNIGHT_BLACK(Knight.from(BLACK), "N", "/images/knight_black.png"),
    KNIGHT_WHITE(Knight.from(WHITE), "n", "/images/knight_white.png"),
    PAWN_BLACK(Pawn.from(BLACK), "P", "/images/pawn_black.png"),
    PAWN_WHITE(Pawn.from(WHITE), "p", "/images/pawn_white.png"),
    ;

    private final ChessPiece chessPiece;
    private final String consoleName;
    private final String WebImagePath;

    PieceName(final ChessPiece chessPiece, final String consoleName, final String webImagePath) {
        this.chessPiece = chessPiece;
        this.consoleName = consoleName;
        WebImagePath = webImagePath;
    }

    public static String findWebImagePath(ChessPiece chessPiece) {
        return Arrays.stream(values())
                .filter(it -> it.chessPiece.equals(chessPiece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 기물이 없습니다."))
                .WebImagePath;
    }

    public static String findConsoleName(final ChessPiece chessPiece) {
        return Arrays.stream(values())
                .filter(it -> it.chessPiece.equals(chessPiece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 기물이 없습니다."))
                .consoleName;
    }
}
