package chess.domain.piece;

import chess.domain.board.Position;

public enum PieceType {
    BISHOP,
    KING,
    KNIGHT,
    PAWN,
    QUEEN,
    ROOK,
    EMPTY;

    public Piece of(Team team, Position position) {
        if (this == BISHOP) {
            return new Bishop(team, position);
        }
        if (this == KING) {
            return new King(team, position);
        }
        if (this == KNIGHT) {
            return new Knight(team, position);
        }
        if (this == PAWN) {
            return new Pawn(team, position);
        }
        if (this == QUEEN) {
            return new Queen(team, position);
        }
        if (this == ROOK) {
            return new Rook(team, position);
        }
        return EMPTY.of(team, position);
    }
}
