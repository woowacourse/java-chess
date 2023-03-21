package chess.view;

import chess.domain.pieces.Bishop;
import chess.domain.pieces.BlackPawn;
import chess.domain.pieces.Empty;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import chess.domain.pieces.Team;
import chess.domain.pieces.WhitePawn;
import java.util.Arrays;

enum ViewPiece {
    WHITE_PAWN(new WhitePawn(Team.WHITE), "p"),
    WHITE_ROOK(new Rook(Team.WHITE), "r"),
    WHITE_KNIGHT(new Knight(Team.WHITE), "n"),
    WHITE_BISHOP(new Bishop(Team.WHITE), "b"),
    WHITE_QUEEN(new Queen(Team.WHITE), "q"),
    WHITE_KING(new King(Team.WHITE), "k"),
    BLACK_PAWN(new BlackPawn(Team.BLACK), "p"),
    BLACK_ROOK(new Rook(Team.BLACK), "r"),
    BLACK_KNIGHT(new Knight(Team.BLACK), "n"),
    BLACK_BISHOP(new Bishop(Team.BLACK), "b"),
    BLACK_QUEEN(new Queen(Team.BLACK), "q"),
    BLACK_KING(new King(Team.BLACK), "k"),
    EMPTY(new Empty(Team.EMPTY), ".");
    ;


    final Piece piece;
    final String name;

    ViewPiece(final Piece piece, final String name) {
        this.piece = piece;
        this.name = name;
    }

    public static String getName(Piece piece) {
        ViewPiece viewPiece = findPiece(piece);
        return viewPiece.name;
    }

    public static ViewPiece findPiece(Piece piece) {
        return Arrays.stream(ViewPiece.values())
            .filter(p -> p.piece.equals(piece))
            .findFirst()
            .get();


    }
}
