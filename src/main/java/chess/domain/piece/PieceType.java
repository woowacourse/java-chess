package chess.domain.piece;

import java.util.function.Function;

import chess.domain.game.Team;

public enum PieceType {
    KING(King::new),
    QUEEN(Queen::new),
    PAWN(Pawn::new),
    ROOK(Rook::new),
    BISHOP(Bishop::new),
    KNIGHT(Knight::new),
    EMPTY(team -> EmptyPiece.create());

    private final Function<Team, Piece> pieceConstructor;

    PieceType(Function<Team, Piece> pieceConstructor) {
        this.pieceConstructor = pieceConstructor;
    }

    public Piece create(Team team) {
        return pieceConstructor.apply(team);
    }
}
