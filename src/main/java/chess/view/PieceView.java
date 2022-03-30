package chess.view;

import static chess.model.Team.BLACK;
import static chess.model.Team.NONE;
import static chess.model.Team.WHITE;

import chess.model.piece.Bishop;
import chess.model.piece.BlackPawn;
import chess.model.piece.Blank;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.piece.WhitePawn;
import java.util.Arrays;

public enum PieceView {

    BLACKKING(new King(BLACK), "♚"),
    BLACKQUEEN(new Queen(BLACK), "♛"),
    BLACKROOK(new Rook(BLACK), "♜"),
    BLACKBISHOP(new Bishop(BLACK), "♝"),
    BLACKKNIGHT(new Knight(BLACK), "♞"),
    BLACKPAWN(new BlackPawn(BLACK), "♟"),
    WHITEKING(new King(WHITE), "♔"),
    WHITEQUEEN(new Queen(WHITE), "♕"),
    WHITEROOK(new Rook(WHITE), "♖"),
    WHITEBISHOP(new Bishop(WHITE), "♗"),
    WHITEKNIGHT(new Knight(WHITE), "♘"),
    WHITEPAWN(new WhitePawn(WHITE), "♙"),
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
