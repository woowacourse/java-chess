package chess.board.piece;

import java.util.Arrays;

public enum Pieces {
    BLACK_KING(new King(Team.BLACK), "K"),
    BLACK_QUEEN(new Queen(Team.BLACK), "Q"),
    BLACK_BISHOP(new Bishop(Team.BLACK), "B"),
    BLACK_KNIGHT(new Knight(Team.BLACK), "N"),
    BLACK_ROOK(new Rook(Team.BLACK), "R"),
    BLACK_PAWN(new Pawn(Team.BLACK), "P"),
    WHITE_KING(new King(Team.WHITE), "k"),
    WHITE_QUEEN(new Queen(Team.WHITE), "q"),
    WHITE_BISHOP(new Bishop(Team.WHITE), "b"),
    WHITE_KNIGHT(new Knight(Team.WHITE), "n"),
    WHITE_ROOK(new Rook(Team.WHITE), "r"),
    WHITE_PAWN(new Pawn(Team.WHITE), "p"),
    BLANK(new Blank(), ".");

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

    public Piece getPiece() {
        return piece;
    }
}
