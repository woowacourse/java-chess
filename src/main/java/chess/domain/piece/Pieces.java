package chess.domain.piece;

import java.util.Arrays;

public enum Pieces {
    BLACK_KING(new King(Team.BLACK)),
    BLACK_QUEEN(new Queen(Team.BLACK)),
    BLACK_BISHOP(new Bishop(Team.BLACK)),
    BLACK_KNIGHT(new Knight(Team.BLACK)),
    BLACK_ROOK(new Rook(Team.BLACK)),
    BLACK_NOT_MOVED_PAWN(new NotMovedPawn(Team.BLACK)),
    BLACK_MOVED_PAWN(new MovedPawn(Team.BLACK)),
    WHITE_KING(new King(Team.WHITE)),
    WHITE_QUEEN(new Queen(Team.WHITE)),
    WHITE_BISHOP(new Bishop(Team.WHITE)),
    WHITE_KNIGHT(new Knight(Team.WHITE)),
    WHITE_ROOK(new Rook(Team.WHITE)),
    WHITE_NOT_MOVED_PAWN(new NotMovedPawn(Team.WHITE)),
    WHITE_MOVED_PAWN(new MovedPawn(Team.WHITE)),
    BLANK(new Blank());

    private final Piece piece;

    Pieces(final Piece piece) {
        this.piece = piece;
    }

    public static <T> Piece findBy(Class<T> type, Team team) {
        return Arrays.stream(Pieces.values())
                .filter(aPiece -> aPiece.isSamePiece(type, team))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .piece;
    }

    public Piece getPiece() {
        return piece;
    }

    private <T> boolean isSamePiece(Class<T> type, Team team) {
        return this.piece.getClass().equals(type) && this.getPiece().isSameTeam(team);
    }
}
