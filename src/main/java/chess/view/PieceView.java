package chess.view;

import static chess.model.Team.BLACK;
import static chess.model.Team.NONE;
import static chess.model.Team.WHITE;

import chess.model.piece.Bishop;
import chess.model.piece.Blank;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.piece.Pawn;
import java.util.Arrays;

public enum PieceView {

    BLACK_KING(new King(BLACK), "♚"),
    BLACK_QUEEN(new Queen(BLACK), "♛"),
    BLACK_ROOK(new Rook(BLACK), "♜"),
    BLACK_BISHOP(new Bishop(BLACK), "♝"),
    BLACK_KNIGHT(new Knight(BLACK), "♞"),
    BLACK_PAWN(new Pawn(BLACK), "♟"),
    WHITE_KING(new King(WHITE), "♔"),
    WHITE_QUEEN(new Queen(WHITE), "♕"),
    WHITE_ROOK(new Rook(WHITE), "♖"),
    WHITE_BISHOP(new Bishop(WHITE), "♗"),
    WHITE_KNIGHT(new Knight(WHITE), "♘"),
    WHITE_PAWN(new Pawn(WHITE), "♙"),
    BLANK(new Blank(NONE), "\uD83D\uDE7E"),
    ;

    private final Piece piece;
    private final String symbol;

    PieceView(Piece piece, String symbol) {
        this.piece = piece;
        this.symbol = symbol;
    }

    public static void printSymbolOf(Piece piece) {
        String symbol = Arrays.stream(values())
                .filter(value -> value.piece.equals(piece))
                .map(value -> value.symbol)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 기물이 없습니다."));

        System.out.print(symbol);
    }
}
