package chess.domain.piece;

import chess.domain.piece.implementation.piece.Bishop;
import chess.domain.piece.implementation.piece.King;
import chess.domain.piece.implementation.piece.Knight;
import chess.domain.piece.implementation.piece.Pawn;
import chess.domain.piece.implementation.piece.Queen;
import chess.domain.piece.implementation.piece.Rook;
import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.function.BiFunction;

public enum PieceType {

    PAWN(1d, Pawn::of),
    KNIGHT(2.5d, Knight::of),
    BISHOP(3d, Bishop::of),
    ROOK(5d, Rook::of),
    QUEEN(9d, Queen::of),
    KING(0d, King::of);

    private double point;
    private BiFunction<Position, Team, PieceState> creator;

    PieceType(final double point, final BiFunction<Position, Team, PieceState> creator) {
        this.point = point;
        this.creator = creator;
    }

    public PieceState apply(Position position, Team team) {
        return creator.apply(position, team);
    }

    public double getPoint() {
        return point;
    }

    public boolean isSameType(PieceType pieceType) {
        return this == pieceType;
    }
}
