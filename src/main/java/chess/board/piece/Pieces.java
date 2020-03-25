package chess.board.piece;

import java.util.Arrays;

public enum Pieces {
    BLACK_KING(new King(Piece.Team.BLACK), "K"),
    BLACK_QUEEN(new Queen(Piece.Team.BLACK), "Q"),
    BLACK_BISHOP(new Bishop(Piece.Team.BLACK), "B"),
    BLACK_KNIGHT(new Knight(Piece.Team.BLACK), "N"),
    BLACK_ROOK(new Rook(Piece.Team.BLACK), "R"),
    BLACK_PAWN(new Pawn(Piece.Team.BLACK), "P"),
    WHITE_KING(new King(Piece.Team.WHITE), "k"),
    WHITE_QUEEN(new Queen(Piece.Team.WHITE), "q"),
    WHITE_BISHOP(new Bishop(Piece.Team.WHITE), "b"),
    WHITE_KNIGHT(new Knight(Piece.Team.WHITE), "n"),
    WHITE_ROOK(new Rook(Piece.Team.WHITE), "r"),
    WHITE_PAWN(new Pawn(Piece.Team.WHITE), "p"),
    EMPTY(null, ".");

    private final Piece piece;
    private final String token;

    Pieces(final Piece piece, final String token) {
        this.piece = piece;
        this.token = token;
    }

    public static Piece findByToken(String token) {
        return Arrays.stream(Pieces.values())
                .filter(aPiece -> aPiece.token.equals(token))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .piece;
    }
}
