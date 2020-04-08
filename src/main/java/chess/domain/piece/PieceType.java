package chess.domain.piece;

import chess.domain.player.Player;
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
    private BiFunction<Position, Player, PieceState> creator;

    PieceType(double point, BiFunction<Position, Player, PieceState> creator) {
        this.point = point;
        this.creator = creator;
    }

    public PieceState apply(Position position, Player player) {
        return creator.apply(position, player);
    }


    public double getPoint() {
        return point;
    }
}
